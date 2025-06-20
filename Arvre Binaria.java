﻿public class No {
   String valor;
   No esquerda, direita;


   public No(String valor){
       this.valor = valor;
       esquerda = direita = null;
   }


}










public class Tree {
   No raiz;


   public int contarNos (No node){
       if (node == null) return 0;
       return 1 + contarNos(node.esquerda) + contarNos(node.direita);
   }


   public void preOrder (No node){
       if (node == null)
           return;
       System.out.println(node.valor + " ");
       preOrder(node.esquerda);
       preOrder(node.direita);
   }


   public void inOrder (No node){
       if (node == null)
           return;
       inOrder(node.esquerda);
       System.out.println(node.valor + " ");
       inOrder(node.direita);
   }


   public void postOrder (No node){
       if (node == null)
           return;
       postOrder(node.esquerda);
       postOrder(node.direita);
       System.out.println(node.valor + " ");
   }


   public void inLevel (No node){
       if (node == null)
           return;
       System.out.println(node.valor);
   }


}





Iterativo
import java.util.*;
public class Tree {
  
   public static void preOrder(No root) {
       if (root == null) return;


       Stack<No> stack = new Stack<>();
       stack.push(root);


       while (!stack.isEmpty()) {
           No current = stack.pop();
           System.out.print(current.valor + " ");
          
           if (current.direita != null) stack.push(current.direita);
           if (current.esquerda != null) stack.push(current.esquerda);
       }
   }
  
   public static void inOrder(No root) {
       Stack<No> stack = new Stack<>();
       No current = root;


       while (current != null || !stack.isEmpty()) {
           while (current != null) {
               stack.push(current);
               current = current.esquerda;
           }


           current = stack.pop();
           System.out.print(current.valor + " ");
           current = current.direita;
       }
   }
  
   public static void postOrder(No root) {
       if (root == null) return;


       Stack<No> stack1 = new Stack<>();
       Stack<No> stack2 = new Stack<>();


       stack1.push(root);


       while (!stack1.isEmpty()) {
           No current = stack1.pop();
           stack2.push(current);


           if (current.esquerda != null) stack1.push(current.esquerda);
           if (current.direita != null) stack1.push(current.direita);
       }


       while (!stack2.isEmpty()) {
           System.out.print(stack2.pop().valor + " ");
       }
   }
  
   public static void levelOrder(No root) {
       if (root == null) return;


       Queue<No> queue = new LinkedList<>();
       queue.add(root);


       while (!queue.isEmpty()) {
           No current = queue.poll();
           System.out.print(current.valor + " ");


           if (current.esquerda != null) queue.add(current.esquerda);
           if (current.direita != null) queue.add(current.direita);
       }
   }
}


int contarNosPilha(No raiz){
  
   Stack<No> pilha = new Stack<>();
   pilha.push(raiz);
  
   int contador = 0;
  
   while (!pilha.isEmpty()) {
       No atual = pilha.pop();
       contador++;
      
       if (atual.direita != null) pilha.push(atual.direita);
       if (atual.esquerda != null) pilha.push(atual.esquerda);
   }
   return contador;
}




int contarNosFila(No raiz){
   Queue<No> fila = new LinkedList<>() {
   fila.add(raiz);


       int contador = 0;


   while(!fila.isEmpty()){
           No atual = fila.poll();
           contador++;


           if (atual.esquerda != null) {
               fila.add(atual.esquerda);
           }
           if (atual.direita != null) {
               fila.add(atual.direita);
           }
       }
   return contador;


   }






Árvore AVL:


No:

public class No {
   int bal, altura;
   int valor;
   No esquerda, direita;


   public No(String valor){
       this.valor = valor;
       esquerda = direita = null;
   }


}




Árvore(incompleta e com erros):
class ArvreAVL {


   No raiz;


   int altura(No node) {
       if (node == null)
           return 0;
       return node.altura;
   }


   int max(int a, int b) {
       return (a > b) ? a : b;
   }


   No rotacaoDireita(No node) {
       No filhoEsquerda = node.esquerda;
       No temp = filhoEsquerda.direita;


       filhoEsquerda.direita = node;
       node.esquerda = temp;


       node.altura = max(altura(node.esquerda), altura(node.direita)) + 1;
       filhoEsquerda.altura = max(altura(filhoEsquerda.esquerda), altura(filhoEsquerda.direita)) + 1;


       // Return new root
       return filhoEsquerda;
   }


   No rotacaoEsquerda(No node) {
       No filhoDireita = node.direita;
       No temp = filhoDireita.esquerda;


       filhoDireita.esquerda = node;
       node.direita = temp;


       node.altura = max(altura(node.esquerda), altura(node.direita)) + 1;
       filhoDireita.altura = max(altura(filhoDireita.esquerda), altura(filhoDireita.direita)) + 1;


       return filhoDireita;
   }


   int balanceamento(No node) {
       if (node == null)
           return 0;
       return altura(node.esquerda) - altura(node.direita);
   }


   No insert(No raiz, int valor) {
       if (raiz == null)
           return new No(raiz.valor);


       if (valor < raiz.valor)
           raiz.esquerda = insert(raiz.esquerda, valor);
       else if (valor > raiz.valor)
           raiz.direita = insert(raiz.valor, valor);
       else
           return raiz;


       raiz.altura = 1 + max(altura(raiz.esquerda), altura(raiz.direita));


       int bal = balanceamento(raiz);
