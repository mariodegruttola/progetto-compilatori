#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void fun1(char* x, char **returnTemp0) {
printf("%s", x);
*returnTemp0 = "Ciccio0";
}
void fun2(char* x, char **returnTemp0) {
x = "Ciccio2\n";
printf("%s", x);
*returnTemp0 = "Ciccio3";
}
int main(void) {
char *b = "Ciccio1";
char *a;
char *varTemp0;
fun2(b, &varTemp0);
a = varTemp0;
printf("%s%s%s%s%s", "a: ", a, " - b: ", b, "\n");
if (a == b) {
printf("%s", "a e b sono uguali\n");
}
else {
printf("%s", "a e b sono diversi\n");
}
return 0;
}
