package section2;

public class Array {
	public static void printAllElements(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * Moves element of the array from startIndex to targetIndex
	 * @param array
	 * @param startIndex
	 * @param targetIndex
	 */
	public static void insertElementAtIndex(int[] array, int startIndex, int targetIndex) {
		int value = array[startIndex];
		if(startIndex == targetIndex) {
			return;
		}
		// shift elements to the left
		else if(startIndex < targetIndex) {
			for(int i = startIndex + 1; i <= targetIndex; i++) {
				array[i - 1] = array[i];
			}
			array[targetIndex] = value;
		}
		// shift elements to the right
		else {
			for(int i = startIndex - 1; i >= targetIndex; i--) {
				array[i + 1] = array[i];
			}
			array[targetIndex] = value;
		}
	}
	
	public static int[] insertExtraElementAtIndex(int[] array, int index, int value) {
		int[] newArray = new int[array.length + 1];
		for(int i = 0; i < array.length; i++) {
			if(i < index){
				newArray[i] = array[i];
			}
			else if(i == index) {
				newArray[i] = value;
				newArray[i + 1] = array[i];
			}
			else if(i > index) {
				newArray[i + 1] = array[i];
			}
		}
		return newArray;
	}
	
	public static void main(String[] args) {
		int[] array = {1,2,3,4,5,6,7,8};
		// 1 2 3 4 5 6 7 8
		printAllElements(array);
		insertElementAtIndex(array, 2, 6);
		// 1 2 4 5 6 7 3 8 
		printAllElements(array);
		int[] array2 = insertExtraElementAtIndex(array, 1, 10);
		// 1 10 2 4 5 6 7 3 8 
		printAllElements(array2);
	}
}
