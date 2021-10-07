#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void somma(int x, int y, int* returnTemp0) {
*returnTemp0 = x + y;
}
void duplica(int x, int* returnTemp0, int* returnTemp1) {
*returnTemp0 = x;
*returnTemp1 = x;
}
void fun1(int x, int* returnTemp0) {
*returnTemp0 = x;
}
int main(void) {
int x = 5, y = 3;
int varTemp0;
int varTemp1;
int varTemp2;
somma(x, y, &varTemp2);
fun1(varTemp2, &varTemp1);
int varTemp3;
fun1(10, &varTemp3);
somma(varTemp1, y + varTemp3, &varTemp0);
printf("%s%d%s%d%s%d%s", "Ciao Mario il valore di x è ", x, " e quello di y è ", y, ". Il risultato della funzione è ", varTemp0, "\n");
return 0;
}
