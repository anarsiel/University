"use strict";

let expression = (function () {

    let variables = {
        'x': 0,
        'y': 1,
        'z': 2
    };
    let operations = {};


    let Primitive = function () {
        this.prefix = function () {
            return this.value.toString();
        };
        this.toString = function () {
            return this.value.toString();
        };
    };
    let Const = function (value) {
        this.value = value;
    };
    Const.prototype = new Primitive();
    Const.prototype.evaluate = function () {
        return this.value;
    };
    Const.prototype.diff = function () {
        return Const.ZERO;
    };

    Const.ZERO = new Const(0);
    Const.ONE = new Const(1);
    Const.TWO = new Const(2);

    let Variable = function (name) {
        this.value = name;
        this.index = variables[this.value];
    };
    Variable.prototype = new Primitive();
    Variable.prototype.evaluate = function () {
        return arguments[this.index];
    };
    Variable.prototype.diff = function (name) {
        return name === this.value ? Const.ONE : Const.ZERO;
    };

    let Creator = function () {
        this.requested = {};
        this.get = function (name) {
            if (!(name in this.requested)) {
                this.requested[name] = new Variable(name);
            }
            return this.requested[name];
        }
    };
    let variableCreator = new Creator();

    let AbstractOperator = function (symbol, operation, derivative) {
        this.op = function (index) {
            return this.operands[index];
        };

        this.prefix = function () {
            return '(' + symbol + ' ' + this.operands.map(function (elem) {
                return elem.prefix();
            }).join(' ') + ')';
        };
        this.toString = function () {
            return this.operands.join(' ') + ' ' + symbol;
        };
        this.evaluate = function () {
            let args = arguments;
            return operation(...this.operands.map((operand) => {
                return operand.evaluate.apply(operand, args);
            }));
        };
        this.diff = function (name) {
            return derivative.call(this, name);
        };
    };

    let operatorFactory = function (symbol, operation, derivative) {
        let Operator = function () {
            this.operands = Array.from(arguments);
        };
        Operator.prototype = new AbstractOperator(symbol, operation, derivative);

        operations[symbol] = {
            op: Operator,
            opCount: operation.length
        };
        return Operator;
    };

    let Negate = operatorFactory(
        'negate',
        function (a) {
            return -a;
        },
        function (name) {
            return new Negate(this.op(0).diff(name));
        }
    );

    let Add = operatorFactory(
        '+',
        function (a, b) {
            return a + b;
        },
        function (name) {
            return new Add(this.op(0).diff(name), this.op(1).diff(name));
        }
    );

    let Subtract = operatorFactory(
        '-',
        function (a, b) {
            return a - b;
        },
        function (name) {
            return new Subtract(this.op(0).diff(name), this.op(1).diff(name));
        }
    );

    let Multiply = operatorFactory(
        '*',
        function (a, b) {
            return a * b;
        },
        function (name) {
            return new Add(
                new Multiply(this.op(0), this.op(1).diff(name)),
                new Multiply(this.op(0).diff(name), this.op(1))
            );
        }
    );

    let Divide = operatorFactory(
        '/',
        function (a, b) {
            return a / b;
        },
        function (name) {
            return new Divide(
                new Subtract(
                    new Multiply(this.op(0).diff(name), this.op(1)),
                    new Multiply(this.op(0), this.op(1).diff(name))
                ),
                new Multiply(this.op(1), this.op(1))
            );
        }
    );

    function myNew(constructor, args) {
        let tmp = Object.create(constructor.prototype);
        constructor.apply(tmp, args);
        return tmp;
    }

    let parse = function (str) {
        let stack = [];
        str.trim().split(/\s+/).forEach(token => {
            if (token in operations) {
                stack.push(myNew(operations[token].op,
                    stack.splice(stack.length - operations[token].opCount, operations[token].opCount)));
            } else if (token in variables) {
                stack.push(variableCreator.get(token));
            } else {
                stack.push(new Const(parseInt(token)));
            }
        });
        return stack.pop();
    };
    let exceptions = function () {
        let exceptionFactory = function (msg) {
            let Exception = function (index, token) {
                this.name = msg + " on position " + index + ", where '" + token + "' is";
            };
            Exception.prototype = new Error();
            return Exception;
        };

        let ClosingParenthesisMissingException = exceptionFactory(
            'Closing parenthesis expected'
        );
        let ExpressionEndExpectedException = exceptionFactory(
            'End of expression expected'
        );
        let OperationExpectedException = exceptionFactory(
            'Operation symbol expected'
        );
        let OperandExpectedException = exceptionFactory(
            'Operand expected'
        );

        return {
            ClosingParenthesisMissingException: ClosingParenthesisMissingException,
            ExpressionEndExpectedException: ExpressionEndExpectedException,
            OperationExpectedException: OperationExpectedException,
            OperandExpectedException: OperandExpectedException
        }
    }();

    let Tokenizer = function (str) {
        this.index = 0;
        this.token = '';

        let isWhitespace = function (c) {
            return /\s/.test("" + c);
        };

        this.nextToken = function () {
            while (this.index < str.length && isWhitespace(str[this.index])) {
                this.index++;
            }
            this.token = '';
            if (str[this.index] === '(' || str[this.index] === ')') {
                this.token = str[this.index++];
            } else {
                while (this.index < str.length &&
                !isWhitespace(str[this.index]) && str[this.index] !== '(' && str[this.index] !== ')') {
                    this.token += str[this.index++];
                }
            }
        };
    };

    let parseOperand = function (tokenizer, parseExpression) {
        if (tokenizer.token === '(') {
            return parseExpression();
        } else if (tokenizer.token in variables) {
            let res = variableCreator.get(tokenizer.token);
            tokenizer.nextToken();
            return res;
        } else if (!isNaN(tokenizer.token)) {
            let res = new Const(parseFloat(tokenizer.token));
            tokenizer.nextToken();
            return res;
        } else {
            throw new exceptions.OperandExpectedException(tokenizer.index, tokenizer.token);
        }
    };

    let parsePrefix = function (str) {
        let tokenizer = new Tokenizer(str);

        let parseExpression = function () {
            if (tokenizer.token === '(') {
                tokenizer.nextToken();
                if (!(tokenizer.token in operations)) {
                    throw new exceptions.OperationExpectedException(tokenizer.index, tokenizer.token);
                }
                let operation = operations[tokenizer.token];
                tokenizer.nextToken();
                let args = [];
                for (let i = 0; i < operation.opCount; i++) {
                    args.push(parseOperand(tokenizer, parseExpression));
                }
                if (tokenizer.token !== ')') {
                    throw new exceptions.ClosingParenthesisMissingException(tokenizer.index, tokenizer.token);
                }
                tokenizer.nextToken();
                return myNew(operation.op, args);
            } else {
                return parseOperand(tokenizer, parseExpression);
            }
        };
        tokenizer.nextToken();
        let res = parseExpression();
        if (tokenizer.token !== '') {
            throw new exceptions.ExpressionEndExpectedException(tokenizer.index, tokenizer.token);
        }
        return res;
    };

    return {
        Const: Const,
        Variable: Variable,
        Negate: Negate,

        Add: Add,
        Subtract: Subtract,
        Multiply: Multiply,
        Divide: Divide,

        parse: parse,
        parsePrefix: parsePrefix
    }

})();

let Const = expression.Const;
let Variable = expression.Variable;
let Negate = expression.Negate;
let Add = expression.Add;
let Subtract = expression.Subtract;
let Multiply = expression.Multiply;
let Divide = expression.Divide;
let parse = expression.parse;
let parsePrefix = expression.parsePrefix;