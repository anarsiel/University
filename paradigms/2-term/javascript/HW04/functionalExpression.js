// "use strict"
const cnst = value => () => value;
const variable = name => (...values) => values[variables.indexOf(name)];

const abstractOperation = operation => (...operands) => (...values) => {
    let result = [];
    for (const currentOperand of operands) {
        result.push(currentOperand(...values));
    }
    return operation(...result);
};

const add = abstractOperation((leftOperand, rightOperand) => leftOperand + rightOperand);
const subtract = abstractOperation((leftOperand, rightOperand) => leftOperand - rightOperand);
const multiply = abstractOperation((leftOperand, rightOperand) => leftOperand * rightOperand);
const divide = abstractOperation((leftOperand, rightOperand) => leftOperand / rightOperand);

const negate = abstractOperation(operand => -operand);

const abs = abstractOperation(operand => Math.abs(operand));
const iff = abstractOperation((first, second, third) => (first >= 0 ? second : third));


let variables = ['x', 'y', 'z'];
variables.forEach(function (element, index, array) {
    array[element] = variable(element);
});

let constants = {
    'one': 1,
    'two': 2,
    'pi': Math.PI,
    'e': Math.E
};
Object.keys(constants).forEach(function (cur) {
    this[cur] = cnst(constants[cur]);
});

function isDigit(symbol) {
    return Boolean(symbol >= '0' && symbol <= '9');
}

const parse = expression => (...values) => {
    let tokens = expression.split(/\s+/);

    let operations = {
        '+': [add, 2],
        '-': [subtract, 2],
        '*': [multiply, 2],
        '/': [divide, 2],
        'negate': [negate, 1],
        'abs': [abs, 1],
        'iff': [iff, 3]
    };

    let stack = [];
    tokens.map(function (token) {
        if (token in operations) {
            let args = [];
            let len = stack.length;
            for (let i = len - 1; i >= len - operations[token][1]; --i) {
                args.push(stack.pop());
            }
            args.reverse();
            stack.push(operations[token][0].apply(this, args));
        } else if (isDigit(token[0]) || (token[0] === '-' && token.length !== 1)) {
            stack.push(cnst(parseInt(token)));
        } else if (variables.indexOf(token) !== -1) {
            stack.push(variables[token]);
        } else if (token in constants) {
            stack.push(constants[token]);
        }
    });
    return stack.pop()(...values);
};