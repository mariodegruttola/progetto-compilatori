#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

int n = 0;
void factorial(int n, int* returnTemp0) {
int result = 0;
if (n == 0) {
result = 1;
}
else {
int varTemp0;
factorial(n - 1, &varTemp0);
result = n * varTemp0;
}
*returnTemp0 = result;
}
int main(void) {
printf("%s", "Enter n, or >= 10 to exit: ");
scanf("%d", &n);
while (n < 10) {
int varTemp1;
factorial(n, &varTemp1);
printf("%s%d%s%d%s", "Factorial of ", n, " is ", varTemp1, "\n");
printf("%s", "Enter n, or >= 10 to exit: ");
scanf("%d", &n);
}
return 0;
}
