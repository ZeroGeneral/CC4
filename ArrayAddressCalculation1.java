/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arrayaddresscalculation;

import java.util.Scanner;

public class ArrayAddressCalculation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: Number of dimensions
        System.out.print("Enter the number of dimensions(int): ");
        int d = sc.nextInt();
        int[] dimensions = new int[d];

        // Input: Sizes of each dimension
        System.out.println("Enter the size of each dimension(UB):");
        for (int i = 0; i < d; i++) {
            System.out.print("Dimension " + (i + 1) + ": ");
            dimensions[i] = sc.nextInt();
        }

        // Input: Indices of the element
        System.out.println("Enter the indices of the element(i):");
        int[] indices = new int[d];
        for (int i = 0; i < d; i++) {
            System.out.print("Index for Dimension " + (i + 1) + ": ");
            indices[i] = sc.nextInt();
        }

        // Input: Base Address and Data Type Size
        System.out.print("Enter the base address(Alpha): ");
        int baseAddress = sc.nextInt();
        System.out.print("Enter the size of each element (esize): ");
        int elementSize = sc.nextInt();

        // Compute total number of elements
        int totalElements = 1;
        for (int size : dimensions) {
            totalElements *= size;
        }
        System.out.println("Total number of elements: " + totalElements);

        // Compute address using default memory mapping
        int address = baseAddress + computeAddress(dimensions, indices, elementSize);
        System.out.println("Computed Address: " + address);

        sc.close();
    }

    private static int computeAddress(int[] dimensions, int[] indices, int elementSize) {
        int addressOffset = 0;
        int product = 1;
        for (int i = dimensions.length - 1; i >= 0; i--) {
            addressOffset += indices[i] * product;
            product *= dimensions[i];
        }
        return addressOffset * elementSize;
    }
}
