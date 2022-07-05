// Define a grammar called Hello
grammar Hello;

/*
 * Parser Rules
 */
all_file returns [StringBuilder val]
    : { $val = new StringBuilder(); } (gs=global_statement { $val.append("\n" + $gs.val); })+ EOF
    ;

global_statement returns [StringBuilder val]: fd=function { $val = $fd.val; }
    | assignation { $val = new StringBuilder($assignation.text);};


function returns [StringBuilder val]:
    { $val = new StringBuilder();}
    (fh=function_h {
        if ($fh.arg_types.size() >= 1) {
            $val.append($fh.val);
        }
    })
    (fb=main_function_part {
        if ($fh.arg_types.size() > 1) {
            for (int i = $fh.arg_types.size() - 1; i > 0; i--) {
                $val.append("  ");
                $val.append($fh.arg_types.get(i) + " " + $fb.args_names.get(i - 1) + " = arg" + ($fh.arg_types.size() - i - 1) + ";\n");
            }
            Boolean started = false;
            int cntEQ = 0;
            for (int j = 0; j < $fb.args_conds.size(); j++) {
                if ($fb.args_conds.get(j) != "True") {
                    cntEQ += 1;
                }
            }

            if (cntEQ > 0) {
                $val.append("\n  if ");
                if (cntEQ > 1) {
                    $val.append("(");
                }
                for (int j = 0; j < $fb.args_conds.size(); j++) {
                    if ($fb.args_conds.get(j) == "True") continue;
                    if (started) {
                        $val.append(" && ");
                    }
                    started = true;
                    $val.append("(" + $fb.args_conds.get(j) + ")");
                }
                if (cntEQ > 1) {
                    $val.append(")");
                }
                $val.append(" {\n    return " + $fb.body + ";\n  }\n");
            }
        } else {
            $val.append(") {\n    " + $fb.body + ";\n");
        }
    })?
    (fb=main_function_part {
            if ($fh.arg_types.size() > 1) {
                Boolean started = false;
                    int cntEQ = 0;
                    for (int j = 0; j < $fb.args_conds.size(); j++) {
                        if ($fb.args_conds.get(j) != "True") {
                            cntEQ += 1;
                        }
                    }

                    if (cntEQ > 0) {
                        $val.append("\n  if ");
                        if (cntEQ > 1) {
                            $val.append("(");
                        }
                        for (int j = 0; j < $fb.args_conds.size(); j++) {
                            if ($fb.args_conds.get(j) == "True") continue;
                            if (started) {
                                $val.append(" && ");
                            }
                            started = true;
                            $val.append("(" + $fb.args_conds.get(j) + ")");
                        }
                        if (cntEQ > 1) {
                            $val.append(")");
                        }
                        $val.append(" {\n    return " + $fb.body + ";\n  }\n");
                    } else {
                        $val.append("\n  return " + $fb.body + ";\n");
                    }
            } else {
                $val.append(") {\n    " + $fb.body + ";\n");
            }
        })+
    {$val.append("}\n");};

function_h returns [String val, ArrayList<String> arg_types]:
    WORD DCOLON function_type {
        $arg_types = $function_type.val;
        $val = $arg_types.get(0) + ' ' + $WORD.text + "(";

        for (int i = 0; i < $arg_types.size() - 1; i++) {
            $val += $arg_types.get($arg_types.size() - i - 1) + " arg" + i + (i == $arg_types.size() - 2? ") {\n" : ", ");
        }
    };

main_function_part returns [String body, ArrayList<String> args_names, ArrayList<String> args_conds]
    : WORD ad=arguments ASSGM assignation {
    $body = $assignation.view;
    $args_names = $ad.args_names;
    $args_conds = $ad.args_conds;
};

arguments returns [ArrayList<String> args_names, ArrayList<String> args_conds]:
    argument COMMA ad=arguments {
        $args_names = $ad.args_names;
        $args_names.add($argument.arg_name);
        $args_conds = $ad.args_conds;
        if ($argument.arg_cond != null)
            $args_conds.add($argument.arg_cond);
    }
    | argument {
        $args_names = new ArrayList<String>();
        $args_names.add($argument.arg_name);
        $args_conds = new ArrayList<String>();
        if ($argument.arg_cond != null)
            $args_conds.add($argument.arg_cond);
    }
    | {$args_names = null; $args_conds = null;};

argument returns [String arg_name, String arg_cond]
    : OPENBRACE oa=argument CLOSEBRACE { $arg_name = $oa.arg_name; $arg_cond = $oa.arg_cond; }
    | WORD PIPE condition { $arg_name = $WORD.text; $arg_cond = $condition.view; }
    | WORD { $arg_name = $WORD.text; $arg_cond = "True"; };


function_usage returns [String view]
    :
        {
            $view = "";
        }
        WORD OPENBRACE {
            $view += $WORD.text + $OPENBRACE.text;
        }(
            expression (COMMA expression {$view += $COMMA.text + $expression.view;})*
            {$view += $expression.view;}
        )? CLOSEBRACE {$view += $CLOSEBRACE.text;};

function_type returns [ArrayList<String> val]:
    BASE_TYPE                 {$val = new ArrayList<String>(); $val.add($BASE_TYPE.text);}
    | BASE_TYPE ARROW tp=function_type  {$val = $tp.val; $val.add($BASE_TYPE.text);};

element returns [String view]
    : INTEGER {$view = $INTEGER.text;}
    | DOUBLE {$view = $DOUBLE.text;}
    | BOOL {$view = $BOOL.text;}
    | WORD {$view = $WORD.text;}
    | function_usage {$view = $function_usage.view;};

assignation returns [String view]
    : element ASSGM expression {$view = $element.view + $ASSGM + $expression.view;}
    | expression {$view = $expression.view;};

expression returns [String view]:
    element {$view = $element.view;} |
    e1=expression FAC {$view = "fact(" + $e1.view + ") ";} |
    e1=expression op=(MUL | DIV | MOD) e2=expression {$view = $e1.view + " " + $op.text + " " + $e2.view;} |
    e1=expression op=(ADD | SUB) e2=expression {$view = $e1.view + " " + $op.text + " " + $e2.view;} ;

condition_operation : EQ | NEQ | LES | GRE | LE | GE | AND | OR ;

condition returns [String view]:
    expression condition_operation condition {$view = $expression.view + $condition_operation.text + $condition.view;}|
    expression {$view = $expression.view;};


/*
 * Lexer Rules
 */
BASE_TYPE : 'Integer' | 'Double' | 'Void';
INTEGER   : '-'?[0-9]+;
DOUBLE  : INTEGER ('.' [0-9]*)?;
BOOL : 'True' | 'False';
WORD : [a-zA-Z][a-zA-Z0-9]*;
ANY_WORD : [a-zA-Z0-9]+;

WHITESPACE : [ \t\r\n]+ -> skip;

COMMA      : ',';
OPENBRACE  : '(';
CLOSEBRACE : ')';
COLON      : ':';
DCOLON     : '::';
SCOLON     : ';';
PIPE       : '|';
ARROW      : '->';

ASSGM : '=';

ADD  : '+';
SUB : '-';
MUL   : '*';
FAC   : '!';
DIV   : '/';
MOD   : '%';

EQ : '==';
NEQ : '!=';
LES : '<';
GRE : '>';
LE : '<=';
GE : '>=';

AND : '&&';
OR : '||';
