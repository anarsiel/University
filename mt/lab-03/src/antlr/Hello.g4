// Define a grammar called Hello
grammar Hello;

/*
 * Parser Rules
 */
code returns [StringBuilder val]
    : { $val = new StringBuilder(); } (gs=global_statement { $val.append("\n" + $gs.val); })+ EOF
    ;

global_statement returns [StringBuilder val]: fd=function_definition { $val = $fd.val; }
    | local_statement { $val = new StringBuilder($local_statement.text);};


function_definition returns [StringBuilder val]:
    { $val = new StringBuilder(); }
    (fh=function_header { if ($fh.arg_types.size() > 1) {$val.append($fh.val);}})
    (fb=function_body {
        if ($fh.arg_types.size() > 1) {
            $val.append("{\n  ");
            for (int i = $fh.arg_types.size() - 1; i > 0; i--) {
                $val.append($fh.arg_types.get(i) + " " + $fb.args_names.get(i - 1) + " = a" + ($fh.arg_types.size() - i) + ";\n  ");
            }
            $val.append("if (");

            for (int j = 0; j < $fb.args_conds.size(); j++) {
                $val.append("(" + $fb.args_conds.get(j) + ")" + (j != $fb.args_conds.size() - 1 ? " && " : ")"));
            }
            $val.append(" {\n    return " + $fb.body + ";\n  }\n}\n");
        } else {
            $val.append($fh.arg_types.get(0) + " " + $fb.args_names.get(0) + " = " + $fb.body);
        }
    })+
    {$val.append("}\n");};

function_header returns [String val, ArrayList<String> arg_types]:
    WORD DCOLON type {
        $arg_types = $type.val;
        $val = $arg_types.get(0) + ' ' + $WORD.text + "(";
        for (int i = $arg_types.size() - 1; i > 0; i--) {
            $val += $arg_types.get(i) + " a" + i + (i == 1 ? ") {\n" : ", ");
        }
    };


function_body returns [String body, ArrayList<String> args_names, ArrayList<String> args_conds]: WORD ad=args_definition EQ local_statement {
    $body = $local_statement.text;
    $args_names = $ad.args_names;
    $args_conds = $ad.args_conds;
};
args_definition returns [ArrayList<String> args_names, ArrayList<String> args_conds]:
    one_arg COMMA ad=args_definition {
        $args_names = $ad.args_names;
        $args_names.add($one_arg.arg_name);
        $args_conds = $ad.args_conds;
        if ($one_arg.arg_cond != null)
            $args_conds.add($one_arg.arg_cond);
    }
    | one_arg {
        $args_names = new ArrayList<String>();
        $args_names.add($one_arg.arg_name);
        $args_conds = new ArrayList<String>();
        if ($one_arg.arg_cond != null)
            $args_conds.add($one_arg.arg_cond);
    }
    | {$args_names = null; $args_conds = null;};
one_arg returns [String arg_name, String arg_cond] : LP oa=one_arg RP { $arg_name = $oa.arg_name; $arg_cond = $oa.arg_cond; }
    | WORD STICK condition { $arg_name = $WORD.text; $arg_cond = $condition.text; }
    | WORD { $arg_name = $WORD.text; $arg_cond = "True"; };


function_calling : WORD LP (expr (COMMA expr)*)? RP;

type returns [ArrayList<String> val]:
    BASE_TYPE                 {$val = new ArrayList<String>(); $val.add($BASE_TYPE.text);}
    | BASE_TYPE '->' tp=type  {$val = $tp.val; $val.add($BASE_TYPE.text);};

data : INTEGER | DOUBLE | BOOL | WORD | function_calling;

local_statement : data EQ expr | expr;

expr :
    data PLUS expr |
    data MINUS expr |
    data DIV expr |
    data MUL expr |
    data OST expr |
    data;

condition :
    expr LESS condition |
    expr GREATER condition |
    expr AND condition |
    expr OR condition |
    expr DEQ condition |
    expr;


/*
 * Lexer Rules
 */
BASE_TYPE : 'Integer' | 'Double';
INTEGER   : '-'?[0-9]+;
DOUBLE  : INTEGER '.' [0-9]*;
BOOL : 'True' | 'False';
WORD : [a-zA-Z] [a-zA-Z0-9]*;

WHITESPACE : [ \t\r\n]+ -> skip;

COMMA  : ',';
LP     : '(';
RP     : ')';
COLON  : ':';
DCOLON : '::';
SCOLON : ';';
EQ     : '=';
STICK  : '|';
ARROW  : '->';

PLUS  : '+';
MINUS : '-';
DIV   : '/';
MUL   : '*';
OST   : '%';

OR : '||';
AND : '&&';
LESS : '<';
GREATER : '>';
DEQ : '==';