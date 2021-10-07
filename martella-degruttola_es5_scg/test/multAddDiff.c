#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

char *nome = "Michele";
void multAddDiff(int* returnTemp0, int* returnTemp1, int* returnTemp2) {
int primo, secondo, mul, add, diff;
printf("%s", "Inserire il primo argomento:\n");
scanf("%d", &primo);
printf("%s", "Inserire il secondo argomento:\n");
scanf("%d", &secondo);
mul = primo * secondo;
add = primo + secondo;
diff = primo - secondo;
*returnTemp0 = mul;
*returnTemp1 = add;
*returnTemp2 = diff;
}
void writeNewLines(int n) {
while (n > 0) {
printf("%s", "\n");
n = n - 1;
}
}
int main(void) {
int a, b, c = 0;
int varTemp0;
int varTemp1;
int varTemp2;
multAddDiff(&varTemp0, &varTemp1, &varTemp2);
a = varTemp0;
b = varTemp1;
c = varTemp2;
printf("%s%s", "Ciao ", nome);
writeNewLines(2);
printf("%s%d%s%d%s%d%s", "I tuoi valori sono:\n", a, " per la moltiplicazione\n", b, " per la somma, e \n", c, " per la differenza");
return 0;
}
