package Brickbreaker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class BrickBreakerGame extends JFrame {
	    private static final int SCREEN_WIDTH = 800;
	    private static final int SCREEN_HEIGHT = 600;
	    private static final int PADDLE_WIDTH = 100;
	    private static final int PADDLE_HEIGHT = 20;
	    private static final int BALL_RADIUS = 10;
	    private static final int BRICK_WIDTH = 80;
	    private static final int BRICK_HEIGHT = 30;
	    private static final int BRICK_ROWS = 5;
	    private static final int BRICK_COLS = 10;
	    private static final int BRICK_PADDING = 5;
	    private static final int BRICK_OFFSET_TOP = 50;
	    private static final int BRICK_OFFSET_LEFT = 50;
	    private static final int BALL_SPEED = 5;
	    private static final int PADDLE_SPEED = 7;

	    private Paddle paddle;
	    private Ball ball;
	    private Brick[][] bricks;

	    public BrickBreakerGame() {
	        setTitle("Brick Breaker");
	        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setResizable(false);
	        setLocationRelativeTo(null);

	        paddle = new Paddle();
	        ball = new Ball();
	        bricks = new Brick[BRICK_ROWS][BRICK_COLS];

	        // Initialize bricks
	        for (int i = 0; i < BRICK_ROWS; i++) {
	            for (int j = 0; j < BRICK_COLS; j++) {
	                bricks[i][j] = new Brick(BRICK_OFFSET_LEFT + j * (BRICK_WIDTH + BRICK_PADDING),
	                                          BRICK_OFFSET_TOP + i * (BRICK_HEIGHT + BRICK_PADDING));
	            }
	        }

	        addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                int key = e.getKeyCode();
	                if (key == KeyEvent.VK_LEFT) {
	                    paddle.moveLeft();
	                } else if (key == KeyEvent.VK_RIGHT) {
	                    paddle.moveRight();
	                }
	            }
	        });

	        Timer timer = new Timer(16, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                update();
	                repaint();
	            }
	        });
	        timer.start();
	    }

	    private void update() {
	        ball.move();
	        checkCollisions();
	        checkGameOver();
	    }

	    private void checkCollisions() {
	        // Check collisions with paddle
	        if (ball.getBounds().intersects(paddle.getBounds())) {
	            ball.reflectOffPaddle();
	        }

	        // Check collisions with bricks
	        for (int i = 0; i < BRICK_ROWS; i++) {
	            for (int j = 0; j < BRICK_COLS; j++) {
	                if (bricks[i][j] != null && ball.getBounds().intersects(bricks[i][j].getBounds())) {
	                    bricks[i][j] = null;
	                    ball.reflectOffBrick();
	                }
	            }
	        }
	    }

	    private void checkGameOver() {
	        // Game over if ball goes below paddle
	        if (ball.getY() > SCREEN_HEIGHT) {
	            JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	            System.exit(0);
	        }

	        // Check if all bricks are destroyed
	        boolean allDestroyed = true;
	        for (int i = 0; i < BRICK_ROWS; i++) {
	            for (int j = 0; j < BRICK_COLS; j++) {
	                if (bricks[i][j] != null) {
	                    allDestroyed = false;
	                    break;
	                }
	            }
	        }
	        if (allDestroyed) {
	            JOptionPane.showMessageDialog(this, "You Win!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
	            System.exit(0);
	        }
	    }

	    @Override
	    public void paint(Graphics g) {
	        super.paint(g);
	        paddle.draw(g);
	        ball.draw(g);
	        for (int i = 0; i < BRICK_ROWS; i++) {
	            for (int j = 0; j < BRICK_COLS; j++) {
	                if (bricks[i][j] != null) {
	                    bricks[i][j].draw(g);
	                }
	            }
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new BrickBreakerGame().setVisible(true);
	            }
	        });
	    }

	    class Paddle {
	        private int x;
	        private int y;

	        public Paddle() {
	            x = (SCREEN_WIDTH - PADDLE_WIDTH) / 2;
	            y = SCREEN_HEIGHT - PADDLE_HEIGHT - 20;
	        }

	        public void moveLeft() {
	            x -= PADDLE_SPEED;
	            if (x < 0) {
	                x = 0;
	            }
	        }

	        public void moveRight() {
	            x += PADDLE_SPEED;
	            if (x + PADDLE_WIDTH > SCREEN_WIDTH) {
	                x = SCREEN_WIDTH - PADDLE_WIDTH;
	            }
	        }

	        public Rectangle getBounds() {
	            return new Rectangle(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
	        }

	        public void draw(Graphics g) {
	            g.setColor(Color.GREEN);
	            g.fillRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
	        }
	    }

	    class Ball {
	        private int x;
	        private int y;
	        private int dx;
	        private int dy;

	        public Ball() {
	            x = SCREEN_WIDTH / 2;
	            y = SCREEN_HEIGHT / 2;
	            dx = BALL_SPEED;
	            dy = BALL_SPEED;
	        }

	        public void move() {
	            x += dx;
	            y += dy;

	            if (x <= 0 || x >= SCREEN_WIDTH - BALL_RADIUS) {
	                dx = -dx;
	            }
	            if (y <= 0 || y >= SCREEN_HEIGHT - BALL_RADIUS) {
	                dy = -dy;
	            }
	        }

	        public Rectangle getBounds() {
	            return new Rectangle(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2);
	        }

	        public int getX() {
	            return x;
	        }

	        public int getY() {
	            return y;
	        }

	        public void reflectOffPaddle() {
	            dy = -dy;
	        }

	        public void reflectOffBrick() {
	            dy = -dy;
	        }

	        public void draw(Graphics g) {
	            g.setColor(Color.RED);
	            g.fillOval(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2);
	        }
	    }

	    class Brick {
	        private int x;
	        private int y;

	        public Brick(int x, int y) {
	            this.x = x;
	            this.y = y;
	        }

	        public Rectangle getBounds() {
	            return new Rectangle(x, y, BRICK_WIDTH, BRICK_HEIGHT);
	        }

	        public void draw(Graphics g) {
	            g.setColor(Color.BLUE);
	            g.fillRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
	        }
	    }
	}





