#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void somma(int x, int y, int* returnTemp0) {
*returnTemp0 = x + y;
}
void moltiplica(int x, int y, int* returnTemp0) {
int count = 1;
int result;
if (y == 0 || x == 0) {
result = 0;
}
else {
result = x;
while (count < y) {
int varTemp0;
somma(result, x, &varTemp0);
result = varTemp0;
count = count + 1;
}
}
*returnTemp0 = result;
}
void dividi(int x, int y, int* returnTemp0) {
*returnTemp0 = x / y;
}
void potenza(int x, int y, int* returnTemp0) {
int result = x, count = 2;
if (y == 0) {
if (x == 0) {
result = 0;
}
else {
result = 1;
}
}
 else if (y > 1) {
while (count <= y) {
int varTemp1;
moltiplica(result, x, &varTemp1);
result = varTemp1;
count = count + 1;
}
}
*returnTemp0 = result;
}
void fibonacci(int x, int* returnTemp0) {
int result;
if (x == 0) {
result = 0;
}
 else if (x == 1) {
result = 1;
}
else {
int varTemp2;
fibonacci(x - 1, &varTemp2);
int varTemp3;
fibonacci(x - 2, &varTemp3);
result = varTemp2 + varTemp3;
}
*returnTemp0 = result;
}
void input(int op, int* returnTemp0, int* returnTemp1) {
int a = 0, b = 0;
if (op >= 1 && op <= 3) {
printf("%s", "\nInserisci primo operando: ");
scanf("%d", &a);
printf("%s", "Inserisci secondo operando: ");
scanf("%d", &b);
}
 else if (op == 4) {
printf("%s", "\nInserisci la base: ");
scanf("%d", &a);
printf("%s", "Inserisci l'esponente: ");
scanf("%d", &b);
}
 else if (op == 5) {
printf("%s", "\nInserisci il numero su cui calcolare la successione di fibonacci: ");
scanf("%d", &a);
}
*returnTemp0 = a;
*returnTemp1 = b;
}
int main(void) {
int x = 0, y = 0, op = 0;
printf("%s", " 1. addizione\n 2. moltiplicazione\n 3. divisione\n 4. elevamento a potenza\n 5. fibonacci\n 6. esci\nInserisci il numero dell'operazione: ");
scanf("%d", &op);
while (op != 6) {
int varTemp4;
int varTemp5;
input(op, &varTemp4, &varTemp5);
x = varTemp4;
y = varTemp5;
if (op == 1) {
int varTemp6;
somma(x, y, &varTemp6);
printf("%s%d%s", "Il risultato della somma è: ", varTemp6, "\n\n");
}
 else if (op == 2) {
int varTemp7;
moltiplica(x, y, &varTemp7);
printf("%s%d%s", "Il risultato della moltiplicazione è: ", varTemp7, "\n\n");
}
 else if (op == 3) {
int varTemp8;
dividi(x, y, &varTemp8);
printf("%s%d%s", "Il risultato della divisione è: ", varTemp8, "\n\n");
}
 else if (op == 4) {
int varTemp9;
potenza(x, y, &varTemp9);
printf("%s%d%s", "Il risultato dell'elevamento a potenza è: ", varTemp9, "\n\n");
}
 else if (op == 5) {
int varTemp10;
fibonacci(x, &varTemp10);
printf("%s%d%s", "Il risultato di fibonacci è: ", varTemp10, "\n\n");
}
else {
printf("%s", "Numero operazione non presente, inserisci un numero che sia compreso tra 1 e 6\n\n");
}
printf("%s", " 1. addizione\n 2. moltiplicazione\n 3. divisione\n 4. elevamento a potenza\n 5. fibonacci\n 6. esci\nInserisci il numero dell'operazione: ");
scanf("%d", &op);
}
return 0;
}
