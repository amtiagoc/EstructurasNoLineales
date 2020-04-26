/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructurasnolineales;

/**
 *
 * @author samaniw
 */
public class EstructurasNoLineales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DGrahp g0 = new DGrahp();
        g0.addNode("A");
        g0.addNode("D");
        g0.addNode("B");
        g0.addNode("C");
        g0.addNode("E");
        g0.addEdge("A", "B", 1);
        g0.addEdge("A", "C", 1);
        g0.addEdge("A", "E", 3);
        g0.addEdge("C", "E", 1);
        
        g0.showAList();
        Graph g1 = new Graph(7);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 3);
        g1.addEdge(2, 3);
        g1.addEdge(2, 4);
        g1.addEdge(2, 5);
        g1.addEdge(3, 6);
        g1.addEdge(4, 6);
        g1.addEdge(4, 5);

        //...
//        g1.deleteEdge(0, 1);
//        g1.deleteEdge(1, 2);
        System.out.println("Matriz de adyacencias");
        System.out.println(g1.showAMatrix());
        System.out.println("Lista de adyacencias");
        System.out.println(g1.showAList());

        System.out.println("Recorrido en profundidad");
        g1.DFS(0);
        System.out.println("");
        System.out.println("Recorrido en anchura");
        g1.BFS(0);
        System.out.println("");
        MaxHeap maxData = new MaxHeap();
        //31,51,53,28,47,22
        maxData.insert(31);
        maxData.insert(51);
        maxData.insert(53);
        maxData.insert(28);
        maxData.insert(47);
        maxData.insert(22);
        System.out.println(maxData.getDataHeap());
        maxData.ExtractMax();
        System.out.println(maxData.getDataHeap());

         //10,5,3,8,20,7,18,25,23,30,21,24   
        BinarySearchTree pino1 = new BinarySearchTree(10);
        try {
            pino1.Add(5);
            pino1.Add(3);    
            pino1.Add(8);    
            pino1.Add(20);    
            pino1.Add(7);    
            pino1.Add(18);    
            pino1.Add(25);    
            pino1.Add(23);    
            pino1.Add(30);    
            pino1.Add(21);    
            pino1.Add(24);
            pino1.InOrden();
            System.out.println("numero de niveles: "+pino1.LastLevel());
            System.out.println("\n");
            pino1.Delete(20);
            pino1.Delete(5);
            pino1.Delete(3);
            pino1.Delete(25);
            pino1.Delete(30);
            pino1.Delete(8);
            pino1.InOrden();
            System.out.println("\n");
            System.out.println("El arbol por niveles: ");
            pino1.LevelOrder();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
