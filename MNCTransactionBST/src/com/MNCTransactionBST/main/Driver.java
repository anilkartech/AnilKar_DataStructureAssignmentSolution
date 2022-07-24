package com.MNCTransactionBST.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Driver {
	static ArrayList<Integer> nodeArrList = new ArrayList<Integer>();

	static class Node {
		int key;
		Node left, right;
	};

	static Node newNode(int data) {
		Node temp = new Node();
		temp.key = data;
		temp.left = null;
		temp.right = null;
		return temp;
	}

	static Node insert(Node root, int key) {
		Node newnode = newNode(key);
		Node x = root;
		Node current_parent = null;
		while (x != null) {
			current_parent = x;
			if (key < x.key)
				x = x.left;
			else if (key > x.key)
				x = x.right;
			else {
				System.out.println("Value already exists!!!");
				return newnode;
			}
		}
		// If the root is null, the tree is empty.
		if (current_parent == null)
			current_parent = newnode;
		// assign new node node to the left child
		else if (key < current_parent.key)
			current_parent.left = newnode;
		// assign the new node to the right child
		else
			current_parent.right = newnode;
		// return pointer to new node
		return current_parent;
	}

	static void Inorder(Node root) {
		if (root == null)
			return;
		else {
			Inorder(root.left);
			System.out.print(root.key + " ");
			nodeArrList.add(root.key);
			Inorder(root.right);
		}
	}

	public static void levelOrder(Node root) {
		Queue<Node> queue1 = new LinkedList<Node>();
		queue1.add(root);
		while (!queue1.isEmpty()) {
			Node tempNode = queue1.poll();
			System.out.print(tempNode.key + " ");
			// enqueue left node
			if (tempNode.left != null) {
				queue1.add(tempNode.left);
			}
			// enqueue right node
			if (tempNode.right != null) {
				queue1.add(tempNode.right);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int value;
		Node root = null;
		int[] arr = { 50, 30, 60, 10, 55 };
		for (int i = 0; i < arr.length; i++) {
			value = arr[i];
			if (root == null) {
				root = insert(root, value);
			} else {
				insert(root, value);
			}
		}
		System.out.println("Inorder of Binary Treee!!\n");
		Inorder(root);
		root = null;
		for (int i = 0; i < nodeArrList.size(); i++) {
			value = nodeArrList.get(i);
			if (root == null) {
				root = insert(root, value);
			} else {
				insert(root, value);
			}
		}
		System.out.println("");
		System.out.println("Level order of Skew Binary Treee!!\n");
		levelOrder(root);
	}

}
