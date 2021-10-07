#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void duplica(int x, int* returnTemp0, int* returnTemp1) {
*returnTemp0 = x;
*returnTemp1 = x;
}
int main(void) {
int x = 5;
int a, b;
int varTemp0;
int varTemp1;
duplica(x, &varTemp0, &varTemp1);
a = varTemp0;
b = varTemp1;
printf("%s%d%s%d%s", "a: ", a, "\nb: ", b, "\n");
return 0;
}
