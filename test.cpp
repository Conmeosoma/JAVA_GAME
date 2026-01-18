#include <iostream>
#include <cmath>
#include <iomanip>

using namespace std;

void nhap(double &a, double &b, double &c) {
    do{
      cout << "Nhap do dai 3 canh cua tam giac: ";
      cin >> a >> b >> c;
    }
    while (a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a);
}

double cosMax(double a, double b, double c) {
  int maxSide = max(a, max(b, c));
  if (a == maxSide) {
      return (b*b + c*c - a*a) / (2 * b * c);
  } else if (b == maxSide) {
      return (a*a + c*c - b*b) / (2 * a * c);
  } else {
      return (a*a + b*b - c*c) / (2 * a * b);
  }
}


double F(double a, double b, double c, int N) {
  double f = 0;
  for (int i = 1; i <= N; i++) {
      f += pow(cosMax(a, b, c), i)/i;
  }
  return f;
}


int main() {
    double a, b, c;
    int N = 33;

    nhap(a, b, c);

    cout << "Gia tri cosin goc C lon nhat: " << fixed << setprecision(2) << cosMax(a, b, c) << endl;

    cout << "Gia tri F(" << a << ", " << b << ", " << c << ", " << N << ") = " << fixed << setprecision(2) << F(a, b, c, N) << endl;

    return 0;
}