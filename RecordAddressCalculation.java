/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recordaddresscalculation;

import java.util.Scanner;

class RecordAddressCalculation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: Number of fields
        System.out.print("Enter the number of fields in the record: ");
        int numFields = sc.nextInt();
        int[] fieldSizes = new int[numFields];
        int[] fieldOffsets = new int[numFields];

        // Input: Sizes and Offsets of Fields
        int currentOffset = 0;
        System.out.println("Enter the size of each field:");
        for (int i = 0; i < numFields; i++) {
            System.out.print("Size of Field " + (i + 1) + " (bytes): ");
            fieldSizes[i] = sc.nextInt();
            fieldOffsets[i] = currentOffset;
            currentOffset += fieldSizes[i];  // Assign offset
        }

        // Input: Base Address
        System.out.print("Enter the base address: ");
        int baseAddress = sc.nextInt();

        // Input: Field number to find address
        System.out.print("Enter the field number to compute its address (1 to " + numFields + "): ");
        int fieldNumber = sc.nextInt();

        if (fieldNumber < 1 || fieldNumber > numFields) {
            System.out.println("Invalid field number.");
        } else {
            int address = baseAddress + fieldOffsets[fieldNumber - 1];
            System.out.println("Address of Field " + fieldNumber + ": " + address);
        }

        sc.close();
    }
}
