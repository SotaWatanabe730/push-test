package bean;

public class ImageData {
	private int id;
	private String materialName;
	private int materialGroup;
	private String imagePath;

	// コンストラクタ
	public ImageData(String materialName, int materialGroup, String imagePath) {
		this.materialName = materialName;
		this.materialGroup = materialGroup;
		this.imagePath = imagePath;
	}

	// ゲッターとセッター
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public int getMaterialGroup() {
		return materialGroup;
	}

	public void setMaterialGroup(int materialGroup) {
		this.materialGroup = materialGroup;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
