package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ButtonPane {
	
	private static HBox buttonBox;
	private Button insertBtn;
	private Button searchBtn;
	private Button updateBtn;
	private Button removeBtn;
	
	
	public ButtonPane() {
		buttonBox = new HBox(50);
		setInsertBtn(new Button("INSERT"));
		setSearchBtn(new Button("SEARCH"));
		setUpdateBtn(new Button("UPDATE"));
		setRemoveBtn(new Button("REMOVE"));
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(getInsertBtn(), getSearchBtn(), getUpdateBtn(), getRemoveBtn());
	}
	
	public static Pane getButtonBox() {
		return buttonBox;
	}

	public Button getInsertBtn() {
		return insertBtn;
	}

	public void setInsertBtn(Button insertBtn) {
		this.insertBtn = insertBtn;
	}

	public Button getSearchBtn() {
		return searchBtn;
	}

	public void setSearchBtn(Button searchBtn) {
		this.searchBtn = searchBtn;
	}

	public Button getRemoveBtn() {
		return removeBtn;
	}

	public void setRemoveBtn(Button removeBtn) {
		this.removeBtn = removeBtn;
	}

	public Button getUpdateBtn() {
		return updateBtn;
	}

	public void setUpdateBtn(Button updateBtn) {
		this.updateBtn = updateBtn;
	}
}
