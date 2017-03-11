package others;

import frames.FrameHand;
import frames.FrameStatus;

public class Controller {

	private FrameStatus status;
	private FrameHand hand;
	
	public Controller() {
		status = new FrameStatus(this);
		hand = new FrameHand(this);
	}
}
