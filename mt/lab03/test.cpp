
Integer sum2Even(Integer arg0, Integer arg1) {
  Integer a = arg0;
  Integer b = arg1;

  if ((b % 2!=1) && (a % 2!=1)) {
    return Error();
  }

  if (fact(b) !=1) {
    return Error();
  }

  return a + b * b - a * a + b;
}

Void mainF() {
    print(abs(5));
}

Integer myFactorial(Integer arg0) {
  Integer x = arg0;

  if (x<0) {
    return Error();
  }

  if (x==0) {
    return 1;
  }

  return fact(1)  + fact(2) ;
}
