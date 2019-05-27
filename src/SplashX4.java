import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;



public class SplashX4 extends JFrame implements Runnable, KeyListener, KeyEventDispatcher {
	public static final long serialVersionUID = 4167944352484193531L;
	public static Canvas canvas = new Canvas();
	public static boolean inFocus = false;
	static JFileChooser chooser;
	public static Path ResourcePath;
	JLabel imageLabel = new JLabel();
	private RenderHandler renderer;
	public static Integer FrameLimit = 30, WindowX = 1000, WindowY = 800;
	public static Thread gameThread;
	public static boolean FirstTime = true, Space = false;
	
	//Instantiate
	public SplashX4(){
		//Locate resource location
		try {
				EventQueue.invokeAndWait(new Runnable() {
				    @Override
				    public void run() {
				        String folder = System.getProperty("user.dir");
				        JFileChooser chooser = new JFileChooser(folder);
				  
				        chooser.setDialogTitle("Locate PNG, JPG, or JPEG.");;
					    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					    chooser.setAcceptAllFileFilterUsed(false);
					    FileNameExtensionFilter filter = new FileNameExtensionFilter(".PNG", "PNG", ".JPG", "JPG", ".JPEG", "JPEG");
					    chooser.setFileFilter(filter);
					    @SuppressWarnings("unused")
						int result = chooser.showOpenDialog(null); 
					  ResourcePath = chooser.getSelectedFile().toPath();
				    }
				});
			} catch (InvocationTargetException e) {
					e.printStackTrace();
			} catch (InterruptedException e) {
					e.printStackTrace();
			}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
		setLocationRelativeTo(null);
		setFocusable(true);
		addKeyListener(this);
		setResizable(true);
		Label l;
		l=new Label();  
		l.setBounds(0,0,0,0);  
		l.setVisible(true);
		add(l);
        add(canvas);
		setVisible(true);
		setTitle("Tiler");
		
		
		canvas.createBufferStrategy(3);
		renderer = new RenderHandler(getWidth(), getHeight());
		
		
		}
	


	@Override
	 public void keyPressed(KeyEvent e) {
		Space = !Space;
		   	}
	 
	@Override public void keyTyped(KeyEvent e) {}	@Override public void keyReleased(KeyEvent e) {}
	
	public void render() {
			WindowY = this.getHeight();
			WindowX = this.getWidth();
			BufferStrategy bufferStrategy = canvas.getBufferStrategy();
			Graphics graphics = bufferStrategy.getDrawGraphics();
			super.paint(graphics);		
			renderer.render(graphics);
			inFocus = this.isFocused();
			graphics.dispose();
			bufferStrategy.show();}
	
	
	public void run() {
		while(true) {
			render();	
			
		}}

	

	public static void main(String[] args){
		
		gameThread = null;
		SplashX4 game = null;
		game = new SplashX4();
		gameThread = new Thread(game);
		gameThread.start();
		}

	


	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
	return false;
	}}