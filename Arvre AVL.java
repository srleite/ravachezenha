﻿class ArvreAVL {


   No raiz;
   public ArvreAVL() {
       this.raiz = null;
   }


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


   public void insert(int valor) {
       this.raiz = insert(this.raiz, valor);
   }


   private No insert(No node, int valor) {
       if (node == null)
           return new No(valor);
      
       if (valor < node.valor)
           node.esquerda = insert(node.esquerda, valor);
       else if (valor > node.valor)
           node.direita = insert(node.direita, valor);
       else
           return node;


      
       node.altura = 1 + max(altura(node.esquerda), altura(node.direita));
      
       int bal = balanceamento(node);
      
       if (bal > 1 && valor < node.esquerda.valor)
           return rotacaoDireita(node);


       if (bal < -1 && valor > node.direita.valor)
           return rotacaoEsquerda(node);


       if (bal > 1 && valor > node.esquerda.valor) {
           node.esquerda = rotacaoEsquerda(node.esquerda);
           return rotacaoDireita(node);
       }


       if (bal < -1 && valor < node.direita.valor) {
           node.direita = rotacaoDireita(node.direita);
           return rotacaoEsquerda(node);
       }


       return node;
   }


   public void preOrder() {
       preOrder(this.raiz);
   }


   private void preOrder(No node) {
       if (node != null) {
           System.out.print(node.valor + " ");
           preOrder(node.esquerda);
           preOrder(node.direita);
       }
   }


   public void inOrder() {
       inOrder(this.raiz);
   }


   private void inOrder(No node) {
       if (node != null) {
           inOrder(node.esquerda);
           System.out.print(node.valor + " ");
           inOrder(node.direita);
       }
   }


   public void postOrder() {
       postOrder(this.raiz);
   }


   private void postOrder(No node) {
       if (node != null) {
           postOrder(node.esquerda);
           postOrder(node.direita);
           System.out.print(node.valor + " ");
       }
   }
}








public class No {
int valor;
int altura;
No esquerda;
No direita;


No(int valor) {
this.valor = valor;
this.altura = 1;
this.esquerda = null;
this.direita = null;
}
}




public class Main {
   public static void main(String[] args) {
       ArvreAVL arvore = new ArvreAVL();


       int[] chaves = {10, 20, 30, 40, 50, 25};


       for (int chave: chaves) {
           arvore.raiz = arvore.insert(arvore.raiz, chave);
       }


       System.out.println("Percurso em ordem da árvore AVL:");
       arvore.inOrder();
   }
}
