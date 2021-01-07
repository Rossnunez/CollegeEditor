package model;

import java.io.Serializable;

public class TextbookBag implements Serializable {
	private Textbook[] textbookBag;
	private int nElems;

	public TextbookBag(int maxSize) {
		textbookBag = new Textbook[maxSize];
		nElems = 0;
	}

	public void insert(Textbook textbook) {
		textbookBag[nElems++] = textbook;
	}

	public Textbook removeById(String isbn) {
		int i;
		for (i = 0; i < nElems; i++) {
			if (textbookBag[i].getIsbn().contentEquals(isbn)) {
				break;
			}
		}

		if (i == nElems) {
			return null;
		} else {
			Textbook temp = textbookBag[i];
			for (int j = i; j < nElems - 1; j++) {
				textbookBag[j] = textbookBag[j + 1];
			}
			nElems--;
			return temp;
		}
	}

	public Textbook findById(String isbn) {
		for (int i = 0; i < nElems; i++) {
			if (textbookBag[i].getIsbn().contentEquals(isbn)) {
				return textbookBag[i];
			}
		}
		return null;
	}
	
	public Textbook[] getTextbook() {
		return textbookBag;
	}

	public void setTextbook(Textbook[] textbook) {
		textbookBag = textbook;
	}

	public int getnElems() {
		return nElems;
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(textbookBag[i]);
		}
	}
}
