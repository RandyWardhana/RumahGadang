/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bangunruanglwjgl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public class Kubus {

    public static final int DISPLAY_WIDTH = 640;
    public static final int DISPLAY_HEIGHT = 480;
    private float rtri;
    private long lastTime;
    private int fps;
    
    public static void main(String[] args) {
        
        Kubus main = null;
        
        try {
            main = new Kubus();
            main.create();
            main.run();
        } catch(LWJGLException e) {}
        
        if (main != null) {
            main.destroy();
        }
        
    }
    
    public void create() throws LWJGLException {
        Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        Display.setTitle("Creating Cube");
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
        
        //Back
        glBegin(GL_QUADS);
        
        glColor3f(1f, 0f, 0f);
        glVertex3f(-1f, -1f, 0f);
        glVertex3f(1f, -1f, 0f);
        glVertex3f(1f, 1f, 0f);
        glVertex3f(-1f, 1f, 0f);
        
        glEnd();
        
        //Front
        glBegin(GL_QUADS);
        
        glColor3f(0f, 1f, 0f);
        glVertex3f(-1f, -1f, 2f);
        glVertex3f(1f, -1f, 2f);
        glVertex3f(1f, 1f, 2f);
        glVertex3f(-1f, 1f, 2f);
        
        glEnd();
        
        //Left
        glBegin(GL_QUADS);
        
        glColor3f(0f, 0f, 1f);
        glVertex3f(-1f, 1f, 0f);
        
        glColor3f(1f, 0f, .1f);
        glVertex3f(-1f, 1f, 2f);
        
        glColor3f(1f, 1f, 0f);
        glVertex3f(-1f, -1f, 2f);
        
        glColor3f(1f, 1f, 1f);
        glVertex3f(-1f, -1f, 0f);
        
        glColor3f(0f, 1f, 0f);
        glVertex3f(-1f, 1f, 0f);
        
        glEnd();
        
        //Right
        glBegin(GL_QUADS);
        
        glColor3f(1f, 0f, 1f);
        glVertex3f(1f, 1f, 0f);
        
        glColor3f(0f, 1f, 0f);
        glVertex3f(1f, 1f, 2f);
        
        glColor3f(1f, 1f, 0f);
        glVertex3f(1f, -1f, 2f);
        
        glColor3f(1f, 1f, 1f);
        glVertex3f(1f, -1f, 0f);
        
        glColor3f(0f, 1f, 0f);
        glVertex3f(1f, 1f, 0f);
        
        glEnd();
        
        //Down
        glBegin(GL_QUADS);
        
        glColor3f(1f, 1f, 1f);
        glVertex3f(1f, -1f, 0f);
        glVertex3f(1f, -1f, 2f);
        glVertex3f(-1f, -1f, 2f);
        glVertex3f(-1f, -1f, 0f);
        
        glEnd();
        
        //Up
        glRotated(-45,1,0,0);
        glTranslatef(0f, -.3f, .7f);
        glBegin(GL_QUADS);
        
        glColor3f(.5f, 0f, 0f);
        
        glVertex3f(-1f, 1f, 0f);
        glVertex3f(1f, 1f, 0f);
        glVertex3f(1f, -.41f, 1.41f);
        glVertex3f(-1f, -.41f, 1.41f);
        
        glEnd();
        
        rtri += 0.2f;
        
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