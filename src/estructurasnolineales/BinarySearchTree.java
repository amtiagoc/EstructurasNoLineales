/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructurasnolineales;

import java.util.ArrayList;

/**
 *
 * @author samaniw
 */
public class BinarySearchTree {

    private BinaryNode root;
    private BinaryNode father;
    private boolean position;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(int data) {
        root = new BinaryNode(data);
    }

    //Punto 1
    public void InOrden() {
        InOrden(root);
    }

    private void InOrden(BinaryNode currentRoot) {
        if (currentRoot != null) {
            InOrden(currentRoot.getLeft());
            System.out.print(currentRoot.getData() + " ");
            InOrden(currentRoot.getRight());
        }
    }

    //Punto 2
    public void Postorden() {
        PostOrden(root);
    }

    private void PostOrden(BinaryNode currentRoot) {
        if (currentRoot != null) {
            PostOrden(currentRoot.getLeft());
            PostOrden(currentRoot.getRight());
            System.out.print(currentRoot.getData() + " ");
        }
    }

    //Punto 3
    public void PreOrden() {
        PreOrden(root);
    }

    private void PreOrden(BinaryNode currentRoot) {
        if (currentRoot != null) {
            System.out.print(currentRoot.getData() + " ");
            PreOrden(currentRoot.getLeft());
            PreOrden(currentRoot.getRight());
        }
    }

    //Punto 4
    public int CountNodes() {
        return CountNodes(root);
    }

    private int CountNodes(BinaryNode root) {
        if (root == null) {
            return 0;
        }
        int Nodes = 0;
        if (root != null) {
            Nodes++;
            Nodes += CountNodes(root.getLeft());
            Nodes += CountNodes(root.getRight());
        }
        return Nodes;
    }

    //Punto 5
    public int CountLeafs() {
        return CountLeafs(root);
    }

    private int CountLeafs(BinaryNode root) {
        if (root == null) {
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        } else {
            return CountLeafs(root.getLeft()) + CountLeafs(root.getRight());
        }
    }

    public void Add(int data) {
        if (root == null) {
            root = new BinaryNode(data);
        } else //validar si el dato ya existe
        {
            if (Search(data) != null) {
                System.out.println("Dato repetido, no se puede insertar");
            } else {
                Add(data, root);

            }
        }
    }

    private void Add(int data, BinaryNode currentRoot) {
        if (data < currentRoot.getData()) {
            if (currentRoot.getLeft() == null) {
                currentRoot.setLeft(new BinaryNode(data));
            } else {
                Add(data, currentRoot.getLeft());
            }

        } else if (currentRoot.getRight() == null) {
            currentRoot.setRight(new BinaryNode(data));
        } else {
            Add(data, currentRoot.getRight());
        }
    }

    public BinaryNode Search(int data) {
        return Search(data, root);
    }

    private BinaryNode Search(int data, BinaryNode currentRoot) {
        if (currentRoot == null) {
            return null;
        }
        if (data == currentRoot.getData()) {
            return currentRoot;
        }

        father = currentRoot;

        if (data < currentRoot.getData()) {
            position = false;
            return Search(data, currentRoot.getLeft());
        } else {
            position = true;
            return Search(data, currentRoot.getRight());
        }
    }

    //Punto 6
    public void Delete(int data) {
        if (root == null) {
            System.out.print("Árbol vacío");
        } else {
            Delete(data, root);
        }
    }

    private void Delete(int data, BinaryNode currentRoot) {
        BinaryNode v = Search(data);
        if (v != null) {
            //SI VA A BORRAR EL UNICO NODO EXISTENTE
            if (v == this.root && v.getLeft() == null && v.getRight() == null) {
                this.root = null;
                return;
            }
            //SI EL NODO ES UNA HOJA
            if (v.isLeaf()) {
                if (position) {
                    this.father.setRight(null);
                } else {
                    this.father.setLeft(null);
                }
            } //SI TIENE UN SOLO HIJO 
            else if (v.hasOneChild()) {
                //SI ESE HIJO ESTÁ A LA DERECHA
                if (v.isChildPosition()) {
                    if (root == v) {
                        BinaryNode minimum = getMinor(v.getRight());
                        Delete(minimum.getData());
                        v.setData(minimum.getData());
                    } //SI EL NODO ESTA A LA DERECHA
                    else if (position) {
                        this.father.setRight(v.getRight());
                    } else {
                        this.father.setLeft(v.getRight());
                    }

                    v.setRight(null);

                } else {
                    if (root == v) {
                        root = v.getLeft();
                        v.setLeft(null);
                    } else if (position) {
                        this.father.setRight(v.getLeft());
                    } else {
                        this.father.setLeft(v.getLeft());
                    }

                    v.setRight(null);

                }
            } else {
                BinaryNode minimum = getMinor(v.getRight());
                Delete(minimum.getData());
                v.setData(minimum.getData());

            }
        }
    }

    //Punto 7
    public int LastLevel() {
        return (LastLevel(root) - 1);
    }

    private int LastLevel(BinaryNode root) {
        int LastRight = (root.getRight() == null ? 0 : 1 + LastLevel(root.getRight()));
        int LastLeft = (root.getLeft() == null ? 0 : 1 + LastLevel(root.getLeft()));
        return (Math.max(LastRight, LastLeft));
    }
    ArrayList<String> levels;

    //Punto 8
    public String LevelOrder() {
        //...
        /* 
        Para mostrar los datos se recomienda usar:
            System.out.print(x.getData()+" ");
        donde x representa un nodo del árbol

        Para generar un salto de linea se recomienda usar:
            System.out.print("\n");
        
         */
        levels = new ArrayList<String>();
        LevelOrder(root, 0);
        String text = "";
        for (int i = 0; i < levels.size(); i++) {
            text += (levels.get(i));
            if (i < levels.size() - 1) {
                text += "\n";
            }
        }
        return text;
    }

    private void LevelOrder(BinaryNode root, int level) {
        if (root != null) {
            LevelOrder(root.getLeft(), level + 1);
            while (level > levels.size() - 1) {
                levels.add("");
            }
            levels.set(level, levels.get(level) + root.getData() + " ");
            LevelOrder(root.getRight(), level + 1);
        }
    }

    public BinaryNode getMinor(BinaryNode currentRoot) {
        if (currentRoot.getLeft() == null) {
            return currentRoot;
        } else {
            return getMinor(currentRoot.getLeft());
        }
    }
}
