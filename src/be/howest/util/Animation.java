package be.howest.util;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import be.howest.game.Handler;
import be.howest.game.ID;
import be.howest.objects.GameObject;


public class Animation extends GameObject{

    private int frameCount;                 // Counts ticks for change
    private int frameDelay;                 // frame delay 1-12 (You will have to play around with this)
    private int currentFrame;               // animations current frame
    private int animationDirection;         // animation direction (i.e counting forward or backward)
    private int totalFrames;                // total amount of frames for your animation

    private boolean stopped;                // has animations stopped

    private List<Frame> frames = new ArrayList<Frame>();    // Arraylist of frames 
	private int centerY;
	private int centerX;
	private int width;
	private int height;
	
	private Handler handler;

    public Animation(int x, int y,int height, int width, ID id,Handler handler, BufferedImage[] frames, int frameDelay) {
        super(x,y,height, width, id);
    	this.frameDelay = frameDelay;
        this.stopped = true;

        for (int i = 0; i < frames.length; i++) {
            addFrame(frames[i], frameDelay);
        }

        this.frameCount = 0;
        this.frameDelay = frameDelay;
        this.currentFrame = 0;
        this.animationDirection = 1;
        this.totalFrames = this.frames.size();
        
        setCenterX(x);
        setCenterY(y);
        this.height = height;
        this.width = width;
        this.handler = handler;
        

    }

    public void start() {
        if (!stopped) {
            return;
        }

        if (frames.size() == 0) {
            return;
        }

        stopped = false;
    }

    public void stop() {
        if (frames.size() == 0) {
            return;
        }

        stopped = true;
    }

    public void restart() {
        if (frames.size() == 0) {
            return;
        }

        stopped = false;
        currentFrame = 0;
    }

    public void reset() {
        this.stopped = true;
        this.frameCount = 0;
        this.currentFrame = 0;
    }

    private void addFrame(BufferedImage frame, int duration) {
        if (duration <= 0) {
            System.err.println("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }

        frames.add(new Frame(frame, duration));
        currentFrame = 0;
    }

    public BufferedImage getSprite() {
        return frames.get(currentFrame).getFrame();
    }

    public void tick() {
        if (!stopped) {
            frameCount++;
            System.out.println("ANIMATION TICK");

            if (frameCount > frameDelay) {
                frameCount = 0;
                currentFrame += animationDirection;
                System.out.println("NEXT FRAME");
                if (currentFrame > totalFrames - 1) {
                    currentFrame = 0;
                    handler.removeObject(this);
                }
                else if (currentFrame < 0) {
                    currentFrame = totalFrames - 1;
                }
            }
        }

    }

	public void render(Graphics g) {
	    g.drawImage(this.getSprite(), x, y, height, width, null);		
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCenterX(int x){
		this.x =  (x - objectWidth / 2);
	}

	public void setCenterY(int y){
		this.y = (y - objectHeight / 2);
	}
}