/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bangunruanglwjgl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 *
 * @author Randy Wardhana
 */
public class BangunRuangLWJGL {

    public static final int DISPLAY_WIDTH = 640;
    public static final int DISPLAY_HEIGHT = 480;
    private float rtri;
    private long lastTime;
    private int fps;
    
    public static void main(String[] args) {
        
        Kubus main = null;
        
        try {
            main = new BangunRuangLWJGL();
            main.create();
            main.run();
        } catch(LWJGLException e) {}
        
        if (main != null) {
            main.destroy();
        }
        
    }
    
    public void create() throws LWJGLException {
        Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        Display.setTitle("Membuat Rumah Gadang");
        Display.setFullscreen(false);
        Display.create();
        initGL();
        resizeGL();
    }
    
    public void destroy() {
        Display.destroy();
    }
    
    public void initGL() {
        glShadeModel(GL_SMOOTH); //Enable Smooth shading
        glClearColor(0f, 0f, 0f, 0f); //Black Background
        glClearDepth(1f); //Depth Buffer Setup
        glEnable(GL_DEPTH_TEST); //Enables Depth Testing
        glDepthFunc(GL_LEQUAL); //The type of Depth Test to do
        
        //Reakky Nice Perspective Calculations
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        
        
        lastTime = Sys.getTime();
    }
    
    public void resizeGL() {
        glViewport(0,0,DISPLAY_WIDTH,DISPLAY_HEIGHT);
        glMatrixMode(GL_PROJECTION); //Select The Projection Matrix
        glLoadIdentity(); //Reset The Projection Matrix
        
        //Calculate The Aspect Ratio of the Window
        
        gluPerspective(45.0f,(float)DISPLAY_WIDTH/(float)DISPLAY_HEIGHT,.1f,50f);
        glMatrixMode(GL_MODELVIEW); //Select The ModelView Matrix
        glLoadIdentity(); //Reset The ModelView Matrix
    }

    
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        glTranslated(0f,0f,-5f); //Translated Pyramid to back
        glRotatef(rtri, 0f, .1f, 0f); //Rotate the Pyramid on it's Y axis
        
        //Koordinat
        
    }


    private void run() {
        while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            if(Display.isVisible()) {
                update();
                render();
            } else {
                if (Display.isDirty()) {
                    render();
                }
                try {
                    Thread.sleep(100);
                } catch(InterruptedException ex) {}
            }
            Display.update();
            Display.sync(60); //fps -> 60
        }
    }
    
    private void update() {
        updateFPS();
    }

    private void updateFPS() {
        if(Sys.getTime()-lastTime>1000) {
            Display.setTitle("Create Cube using OpenGL by Randy W");
            fps = 0;
            lastTime = Sys.getTime();
        }
        fps++;
    }

}
