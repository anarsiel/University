let constants = {
    '0' : new Const(0),
    '1' : new Const(1),
    '2' : new Const(2)
};

function Operation() {
    this.operands = arguments;
}

Operation.prototype.evaluate = function () {
    let res = [];
    for (let i = 0; i < this.operands.length; i++) {
        res.push(this.operands[i].evaluate.apply(this.operands[i], arguments));
    }
    return this.count(...res);
};

Operation.prototype.diff = function (name) {
    let res = [];
    for (let i = 0; i < this.operands.length; i++) {
        res.push(this.operands[i]);
        res.push(this.operands[i].diff(name));
    }
    return this.countDiff.apply(null, res);
};

Operation.prototype.toString = function () {
    let resString = '';

    for (let i = 0; i < this.operands.length; i++) {
        resString += this.operands[i].toString() + ' ';
    }
    return resString + this.operation;
};

function Const(a) {
    this.value = a;
}

Const.prototype.evaluate = function () {
    return this.value;
};

Const.prototype.diff = function () {
    return new Const(0);
};

Const.prototype.toString = function () {
    return this.value.toString();
};

let variables = ['x', 'y', 'z'];

function Variable(name) {
    this.index = variables.indexOf(name);
}

Variable.prototype.evaluate = function () {
    return arguments[this.index];
};

Variable.prototype.diff = function (name) {
    if (variables[this.index] === name)
        return constants["1"];
    else
        return constants["0"];
};

Variable.prototype.toString = function () {
    return variables[this.index];
};

function Add(a, b) {
    Operation.call(this, a, b);
    this.operation = '+'
}

Add.prototype = Object.create(Operation.prototype);
Add.prototype.count = function (a, b) {
    return a + b;
};
Add.prototype.countDiff = function (a, a_dir, b, b_dir) {
    return new Add(a_dir, b_dir);
};

function Subtract(a, b) {
    Operation.call(this, a, b);
    this.operation = '-'
}

Subtract.prototype = Object.create(Operation.prototype);
Subtract.prototype.count = function (a, b) {
    return a - b;
};
Subtract.prototype.countDiff = function (a, a_dir, b, b_dir) {
    return new Subtract(a_dir, b_dir);
};

function Multiply(a, b) {
    Operation.call(this, a, b);
    this.operation = '*'
}

Multiply.prototype = Object.create(Operation.prototype);
Multiply.prototype.count = function (a, b) {
    return a * b;
};
Multiply.prototype.countDiff = function (a, a_dir, b, b_dir) {
    return new Add(new Multiply(a, b_dir), new Multiply(a_dir, b));
};

function Divide(a, b) {
    Operation.call(this, a, b);
    this.operation = '/'
}

Divide.prototype = Object.create(Operation.prototype);
Divide.prototype.count = function (a, b) {
    return a / b;
};

Divide.prototype.countDiff = function (a, a_dir, b, b_dir) {
    return new Divide(new Subtract(new Multiply(a_dir, b), new Multiply(a, b_dir)), new Multiply(b, b));
};

function Negate(a) {
    Operation.call(this, a);
    this.operation = 'negate'
}

Negate.prototype = Object.create(Operation.prototype);
Negate.prototype.count = function (a) {
    return -a;
};

Negate.prototype.countDiff = function (a, a_dir) {
    return new Negate(a_dir);
};

function ArcTan(a) {
    Operation.call(this, a);
    this.operation = 'atan'
}

ArcTan.prototype = Object.create(Operation.prototype);
ArcTan.prototype.count = function (a) {
    return Math.atan(a);
};

ArcTan.prototype.countDiff = function (a, a_dir) {
    return new Multiply(new Divide(constants["1"], new Add(constants["1"], new Multiply(a, a))), a_dir);
};

function ArcTan2(a, b) {
    Operation.call(this, a, b);
    this.operation = 'atan2'
}

ArcTan2.prototype = Object.create(Operation.prototype);
ArcTan2.prototype.count = function (a, b) {
    return Math.atan2(a, b);
};

ArcTan2.prototype.countDiff = function (a, a_dir, b, b_dir) {
    return  new Divide(new Subtract(new Multiply(b, a_dir), new Multiply(a, b_dir)), new Add(new Multiply(a, a), new Multiply(b, b)));
};

function myNew(constructor, args) {
    let tmp = Object.create(constructor.prototype);
    constructor.apply(tmp, args);
    return tmp;
}


function isDigit(symbol) {
    return Boolean(symbol >= '0' && symbol <= '9');
}

let operations = {
    '+': [Add, 2],
    '-': [Subtract, 2],
    '*': [Multiply, 2],
    '/': [Divide, 2],
    'negate': [Negate, 1],
    'atan': [ArcTan, 1],
    'atan2': [ArcTan2, 2]
};

let parse = (exp) => exp.trim().split(/\s+/).reduce(func, []).pop();
let func = (stack, token) => {
    if (token in operations) {
        let args = [];
        for (let j = 0; j < operations[token][1]; j++)
            args.push(stack.pop());
        args.reverse();
        stack.push(myNew(operations[token][0], args));
    } else if (variables.indexOf(token) !== -1) {
        stack.push(new Variable(token));
    } else if (isDigit(token[0]) || (token[0] === '-' && token.length !== 1)) {
        stack.push(new Const(parseInt(token)));
    }
    return stack;
};