package TextGame;

public class Puzzle {
	private String puzzleId;
	private String puzzleDescription;
	private String solution;
	private String faiedMessage;
	private String successMessage;
	private String roomLocation;
	
	public Puzzle(String puzzleId, String puzzleDescription, String solution, String failedMessage, String successMessage, String roomLocation) {
		this.puzzleId = puzzleId;
		this.puzzleDescription= puzzleDescription;
		this.solution = solution;
		this.faiedMessage = failedMessage;
		this.successMessage = successMessage;
		this.roomLocation = roomLocation;
	}

	public String getPuzzleId() {
		return puzzleId;
	}

	public void setPuzzleId(String puzzleId) {
		this.puzzleId = puzzleId;
	}

	public String getPuzzleDescription() {
		return puzzleDescription;
	}

	public void setPuzzleDescription(String puzzleDescription) {
		this.puzzleDescription = puzzleDescription;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getFaiedMessage() {
		return faiedMessage;
	}

	public void setFaiedMessage(String faiedMessage) {
		this.faiedMessage = faiedMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getRoomLocation() {
		return roomLocation;
	}

	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}
	
	
}
