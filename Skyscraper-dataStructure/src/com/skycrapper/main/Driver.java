package com.skycrapper.main;

import java.util.Scanner;
import java.util.Stack;

public class Driver {

	public static Stack<Integer> sortStack(Stack<Integer> stack) {
		Stack<Integer> newStack = new Stack<Integer>();
		while (!stack.isEmpty()) {
			int tmp = stack.pop();
			while (!newStack.isEmpty() && newStack.peek() > tmp) {
				stack.push(newStack.pop());
			}
			newStack.push(tmp);
		}
		return newStack;
	}

	public static boolean compareFloorSizes(int maxFloorSize, Stack<Integer> stack, boolean status,
			Stack<Integer> stackTemp2) {
		for (int floorSize : stack) {
			if (floorSize > maxFloorSize) {
				status = true;
				stackTemp2.add(maxFloorSize);
				stack.remove((Integer) maxFloorSize);
				break;
			}
		}
		return status;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> stackTemp = new Stack<Integer>();
		Stack<Integer> stackFloorReadyTemp = new Stack<Integer>();
		System.out.println("enter the total no of floors in the building");
		int totalNoOfFloors = sc.nextInt();
		for (int i = 1; i <= totalNoOfFloors; i++) {
			System.out.println("enter the floor size given on day: " + i);
			int floorSize = sc.nextInt();
			stack.push(floorSize);
			stackTemp.push(floorSize);
		}
		System.out.println("The order of construction is as follows");
		int maxFloorSize;
		for (int i = 0; i < totalNoOfFloors; i++) {
			boolean status = false;
			int day = i + 1;
			maxFloorSize = stackTemp.get(i);
			System.out.println("Day: " + day);
			status = compareFloorSizes(maxFloorSize, stack, status, stackFloorReadyTemp);
			if (!status) {
				stack.remove((Integer) maxFloorSize);
				stackFloorReadyTemp.add(maxFloorSize);
				@SuppressWarnings("unchecked")
				Stack<Integer> sortedFloorReadyStack = sortStack((Stack<Integer>) stackFloorReadyTemp.clone());
				for (int j = sortedFloorReadyStack.size() - 1; j >= 0; j--) {
					boolean sortedStausFlag = false;
					for (int floorSize : stack) {
						if (floorSize > sortedFloorReadyStack.get(j)) {
							sortedStausFlag = true;
							break;
						}
					}
					if (!sortedStausFlag) {
						System.out.print(sortedFloorReadyStack.get(j) + " ");
						stackFloorReadyTemp.remove((Integer) sortedFloorReadyStack.get(j));
					}
				}
				System.out.println();
			} else {
				System.out.println();
			}
		}
	}

}
