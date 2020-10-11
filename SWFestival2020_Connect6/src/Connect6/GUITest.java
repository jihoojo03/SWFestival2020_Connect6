package Connect6;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;

public class GUITest extends JFrame {
	
	private JPanel gamePanel = new JPanel();
	private JPanel settingPanel = new JPanel();
	private JPanel startPanel = new JPanel();
	
	private ImageIcon boardImage01 = new ImageIcon(Connect6AIMain.class.getResource("../images/board.png"));
	private ImageIcon boardImage02 = new ImageIcon(Connect6AIMain.class.getResource("../images/board02.png"));
	private ImageIcon boardImage03 = new ImageIcon(Connect6AIMain.class.getResource("../images/jihoonBoard.png"));
	private ImageIcon blackStone = new ImageIcon(Connect6AIMain.class.getResource("../images/blackStone.png"));
	private ImageIcon whiteStone = new ImageIcon(Connect6AIMain.class.getResource("../images/whiteStone.png"));
	private ImageIcon blueStone = new ImageIcon(Connect6AIMain.class.getResource("../images/blueStone.png"));
	private ImageIcon yellowStone = new ImageIcon(Connect6AIMain.class.getResource("../images/yellowStone.png"));
	private ImageIcon xMark = new ImageIcon(Connect6AIMain.class.getResource("../images/xMark.png"));
	private ImageIcon buttonImage = new ImageIcon(Connect6AIMain.class.getResource("../images/containButtonEntered.png"));
	private ImageIcon blackStoneTitle = new ImageIcon(Connect6AIMain.class.getResource("../images/blackStoneTitle.png"));
	private ImageIcon whiteFlag = new ImageIcon(Connect6AIMain.class.getResource("../images/whiteFlag.png"));
	private ImageIcon reset = new ImageIcon(Connect6AIMain.class.getResource("../images/reset.png"));
	private ImageIcon back = new ImageIcon(Connect6AIMain.class.getResource("../images/back.png"));
	private ImageIcon exit = new ImageIcon(Connect6AIMain.class.getResource("../images/exit.png"));
	private ImageIcon blackPlayer = new ImageIcon(Connect6AIMain.class.getResource("../images/blackPlayer.png"));
	private ImageIcon whitePlayer = new ImageIcon(Connect6AIMain.class.getResource("../images/whitePlayer.png"));
	private ImageIcon bluePlayer = new ImageIcon(Connect6AIMain.class.getResource("../images/bluePlayer.png"));
	private ImageIcon yellowPlayer = new ImageIcon(Connect6AIMain.class.getResource("../images/yellowPlayer.png"));
	private ImageIcon latelyBlackStone = new ImageIcon(Connect6AIMain.class.getResource("../images/blackStoneSelected.png"));
	private ImageIcon latelyWhiteStone = new ImageIcon(Connect6AIMain.class.getResource("../images/whiteStoneSelected.png"));
	private ImageIcon latelyYellowStone = new ImageIcon(Connect6AIMain.class.getResource("../images/yellowStoneSelected.png"));
	private ImageIcon latelyBlueStone = new ImageIcon(Connect6AIMain.class.getResource("../images/blueStoneSelected.png"));
	private ImageIcon player1Stone = blackStone;
	private ImageIcon player2Stone = whiteStone;
	private ImageIcon player1LatelyStone = latelyBlackStone;
	private ImageIcon player2LatelyStone = latelyWhiteStone;
	
	private JLabel connect6Main = new JLabel("Connect6");
	private JLabel blackStoneMain = new JLabel(blackStoneTitle);
	private JLabel connect6Title = new JLabel("Connect6");
	private JLabel boardLabel = new JLabel(boardImage01);
	private JLabel connect6Title02 = new JLabel("Connect6");
	private JLabel boardLabel02 = new JLabel(boardImage01);
	private JLabel shapeLabel = new JLabel("¹ÙµÏÆÇ ¸ð¾ç");
	private JLabel colorLabel = new JLabel("¹ÙµÏµ¹ »ö±ò");
	private JLabel player1Image = new JLabel(blackPlayer);
	private JLabel player2Image = new JLabel(whitePlayer);
	private JLabel timerLabel = new JLabel("15s");
	private JLabel timerLineLabel = new JLabel(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	private JLabel player1Label = new JLabel("Player 1");
	private JLabel player2Label = new JLabel("Player 2");
	private JLabel timeLimitLabel = new JLabel("Á¦ÇÑ½Ã°£ ¼³Á¤");
	private JLabel timeShowLabel = new JLabel("15s");
	private JLabel optionP1Label = new JLabel(blackStone);
	private JLabel optionP2Label = new JLabel(whiteStone);
	private JLabel AILabel = new JLabel("ÄÄÇ»ÅÍ¶û ºÙ±â");

	private JLabel stoneLabel[][] = new JLabel[19][19];
	private JLabel stoneLabel02[][] = new JLabel[19][19];
	
	private JCheckBox defaultShapeCheckBox = new JCheckBox("±âº»");
	private JCheckBox whiteShapeCheckBox = new JCheckBox("È­ÀÌÆ®");
	private JCheckBox jihoonShapeCheckBox = new JCheckBox("ÁöÈÆ");
	private JCheckBox player1BlackCheckBox = new JCheckBox("Èæ");
	private JCheckBox player1WhiteCheckBox = new JCheckBox("¹é");
	private JCheckBox player1BlueCheckBox = new JCheckBox("Ã»");
	private JCheckBox player1YellowCheckBox = new JCheckBox("³ë");
	private JCheckBox player2BlackCheckBox = new JCheckBox("Èæ");
	private JCheckBox player2WhiteCheckBox = new JCheckBox("¹é");
	private JCheckBox player2BlueCheckBox = new JCheckBox("Ã»");
	private JCheckBox player2YellowCheckBox = new JCheckBox("³ë");
	private JCheckBox player1CheckBox = new JCheckBox("¼±°ø");
	private JCheckBox player2CheckBox = new JCheckBox("ÈÄ°ø");
	
	private JButton settingConfirmButton = new JButton("¼³Á¤ ¿Ï·á");
	private JButton startMainButton = new JButton("°ÔÀÓ ½ÃÀÛ");
	private JButton settingMainButton = new JButton("¼³Á¤");
	private JButton player1Button = new JButton("Player1");
	private JButton player2Button = new JButton("Player2");
	private JButton whiteFlagButton = new JButton(whiteFlag);
	private JButton resetButton = new JButton(reset);
	private JButton backButton = new JButton(back);
	private JButton exitButton = new JButton(exit);
	private JButton resetSettingButton = new JButton(reset);
	
	private JSlider timeLimitSlider = new JSlider(15, 30);
	
	private Timer timer = new Timer();
	private static TimerTask task;
	private static int count = 15;
	
	private boolean buyWhite = false;
	private boolean buyJihoon = false;
	
	private int adjX = 10;
	private int adjY = 10;
	
	GameAlgorithm gameAl = new GameAlgorithm();
	GameOption gameOp = new GameOption();
	
	GUITest() {
		getContentPane().setBackground(new Color(245, 245, 245));
		setTitle("Connect6");
		setSize(Connect6AIMain.SCREEN_WIDTH, Connect6AIMain.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		makeGame();
		makeSetting();
		makeStart();
	}
	
	public void makeGame() {	
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				stoneLabel[i][j] = new JLabel();
				stoneLabel[i][j].setBounds(24 + 28 * i, 24 + 28 * j, 27, 27);
				stoneLabel[i][j].setVisible(true);
				gamePanel.add(stoneLabel[i][j]);
			}
		}
		
		boardLabel.setBackground(new Color(0, 0, 0));
		boardLabel.setBounds(20, 20, 540, 540);
		boardLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				boardLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				boardLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
//				count = gameOp.getTimeLimit();
//				if(gameAl.getAmount() >= 2) task.cancel();
//				task = timerTaskMaker();
//				timer.schedule(task, 0, 1000);
				
				
				Point point = e.getPoint();
				adjX = (point.x - 3) / 28 + 1;
				adjY = (point.y - 3) / 28 + 1;
				
				if(gameAl.isStone(adjX, adjY)) {
					System.out.println("StoneÀÌ ÀÖ½À´Ï´Ù");
				}
				else {
					if(gameAl.isFirst()) {
						gameAl.launch();
						gameAl.setStone(adjX, adjY, 0);
						stoneLabel[adjX - 1][adjY - 1].setIcon(player1Stone);
						
						if(gameAl.getAmount() % 4 == 1) {
							gameAl.showNextMove();
//							gameAl.show();
							
//							gameAl.minimax();
							
							gameAl.findOptimal();
							stoneLabel[gameAl.getNextX1()][gameAl.getNextY1()].setIcon(player2Stone);
							gameAl.showNextMove();
//							gameAl.show();
							
							gameAl.findOptimal();
							stoneLabel[gameAl.getNextX1()][gameAl.getNextY1()].setIcon(player2Stone);
							gameAl.showNextMove();
//							gameAl.show();
						}
						
					}
					else {
						gameAl.launch();
						gameAl.setStone(adjX, adjY, 1);
						stoneLabel[adjX - 1][adjY - 1].setIcon(player2Stone);
						
						if(gameAl.getAmount() % 4 == 3) {
							gameAl.showNextMove();
							
							gameAl.findOptimal();
							stoneLabel[gameAl.getNextX1()][gameAl.getNextY1()].setIcon(player1Stone);
							gameAl.showNextMove();
							
							
							gameAl.findOptimal();
							stoneLabel[gameAl.getNextX1()][gameAl.getNextY1()].setIcon(player1Stone);
							gameAl.showNextMove();
						}			
					}
					
					if(gameAl.win(adjX, adjY) == 6) {
//						task.cancel();
						
						if(gameAl.getCurrentTurn() == 0) {
							makePanel("<html><body> Player1  ½Â¸®! </body></html>");
						}
						else if(gameAl.getCurrentTurn() == 1) {
							makePanel("<html><body> Player2 ½Â¸®! </body></html>");
						}
					}
					
				}
			}
		});	
		gamePanel.add(boardLabel);
		
		connect6Title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		connect6Title.setBounds(228, 570, 108, 31);
		gamePanel.add(connect6Title);
		
		player1Image.setBounds(590, 120, 160, 160);
		player1Image.setVisible(true);
		gamePanel.add(player1Image);
		
		player2Image.setBounds(765, 120, 160, 160);
		player2Image.setVisible(true);
		gamePanel.add(player2Image);
		
		
		timerLineLabel.setBackground(Color.BLACK);
		timerLineLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 20));
		timerLineLabel.setBounds(590, 20, 270, 90);
		timerLineLabel.setVisible(true);
		gamePanel.add(timerLineLabel);
		
		timerLabel.setBackground(Color.BLACK);
		timerLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 20));
		timerLabel.setBounds(885, 20, 40, 90);
		timerLabel.setVisible(true);
		gamePanel.add(timerLabel);
		
		player1Button.setBounds(590, 290, 160, 40);
		player1Button.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 16));
		player1Button.setVisible(true);
		player1Button.setBorderPainted(false);
		player1Button.setContentAreaFilled(true);
		player1Button.setFocusPainted(false);
		player1Button.setBackground(new Color(245, 126, 23));
		gamePanel.add(player1Button);
		
		player2Button.setBounds(765, 290, 160, 40);
		player2Button.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 16));
		player2Button.setVisible(true);
		player2Button.setBorderPainted(false);
		player2Button.setContentAreaFilled(true);
		player2Button.setFocusPainted(false);
		player2Button.setBackground(Color.WHITE);
		gamePanel.add(player2Button);
		
		whiteFlagButton.setBounds(590, 480, 80, 80);
		whiteFlagButton.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 16));
		whiteFlagButton.setVisible(true);
		whiteFlagButton.setBorderPainted(false);
		whiteFlagButton.setContentAreaFilled(false);
		whiteFlagButton.setFocusPainted(false);
		whiteFlagButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				whiteFlagButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				whiteFlagButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				gameAl.resetGame();
				resetGameStoneLabel();
				task.cancel();
				player1Button.setBackground(new Color(245, 126, 23));
				player2Button.setBackground(Color.WHITE);
				
				if(gameAl.getCurrentTurn() == 0) {
					makePanel("<html><body> Player1 ±â±Ç! <br> Player2 ½Â¸®! </body></html>");
				}
				else if(gameAl.getCurrentTurn() == 1) {
					makePanel("<html><body> Player2 ±â±Ç! <br> Player1 ½Â¸®! </body></html>");
				}
				
			}
		});	
		gamePanel.add(whiteFlagButton);
		
		backButton.setBounds(675, 480, 80, 80);
		backButton.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 16));
		backButton.setVisible(false);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		gamePanel.add(backButton);
		
		resetButton.setBounds(760, 480, 80, 80);
		resetButton.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 16));
		resetButton.setVisible(true);
		resetButton.setBorderPainted(false);
		resetButton.setContentAreaFilled(false);
		resetButton.setFocusPainted(false);
		resetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				resetButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
//				task.cancel();
				count = gameOp.getTimeLimit();
				gameAl.resetGame();
				resetGameStoneLabel();
				player1Button.setBackground(new Color(245, 126, 23));
				player2Button.setBackground(Color.WHITE);
			}
		});	
		gamePanel.add(resetButton);
		
		exitButton.setBounds(845, 480, 80, 80);
		exitButton.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 16));
		exitButton.setVisible(true);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
//				if(gameAl.getAmount() >= 1) task.cancel();
				count = gameOp.getTimeLimit();
				gameAl.reset();
				resetAllStoneLabel();
				player1Button.setBackground(new Color(245, 126, 23));
				player2Button.setBackground(Color.WHITE);
				
				gamePanel.setVisible(false);
				startPanel.setVisible(true);
				getContentPane().add(startPanel);
			}
		});	
		gamePanel.add(exitButton);
		
		gamePanel.setLayout(null);
		gamePanel.setVisible(false);
		getContentPane().setVisible(false);
		getContentPane().add(gamePanel);
	}
	
	public void makeSetting() {
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				stoneLabel02[i][j] = new JLabel();
				stoneLabel02[i][j].setBounds(24 + 28 * i, 24 + 28 * j, 27, 27);
				stoneLabel02[i][j].setVisible(true);
				settingPanel.add(stoneLabel02[i][j]);
			}
		}
		
		boardLabel02.setBackground(new Color(0, 0, 0));
		boardLabel02.setBounds(20, 20, 540, 540);
		boardLabel02.setVisible(true);
		boardLabel02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				boardLabel02.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				boardLabel02.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int adjX = (point.x - 3) / 28 + 1;
				int adjY = (point.y - 3) / 28 + 1;
				
				if(gameAl.isStone(adjX, adjY)) {
					System.out.println("StoneÀÌ ÀÖ½À´Ï´Ù");
				}
				else {
					gameAl.setStone(adjX, adjY, 2);
					stoneLabel02[adjX - 1][adjY - 1].setIcon(xMark);
					stoneLabel[adjX - 1][adjY - 1].setIcon(xMark);
					gameAl.show();
				}
				
			}
		});	
		settingPanel.add(boardLabel02);

		connect6Title02.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		connect6Title02.setBounds(228, 570, 108, 31);
		settingPanel.add(connect6Title02);
		
		shapeLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 16));
		shapeLabel.setBounds(600, 30, 75, 15);
		settingPanel.add(shapeLabel);
		
		defaultShapeCheckBox.setSelected(true);
		defaultShapeCheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		defaultShapeCheckBox.setBounds(632, 60, 52, 23);
		defaultShapeCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				whiteShapeCheckBox.setSelected(false);
				jihoonShapeCheckBox.setSelected(false);
				boardLabel02.setIcon(boardImage01);
				boardLabel.setIcon(boardImage01);
				gameOp.setBoardImage(0);
			}
		});
		settingPanel.add(defaultShapeCheckBox);
		
		whiteShapeCheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		whiteShapeCheckBox.setBounds(726, 60, 67, 23);
		whiteShapeCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				defaultShapeCheckBox.setSelected(false);
				jihoonShapeCheckBox.setSelected(false);
				boardLabel02.setIcon(boardImage02);
				boardLabel.setIcon(boardImage02);
				gameOp.setBoardImage(1);
				if(!buyWhite) {
					makePanel("<html><body> ±â¾÷ 01043375460À¸·Î 20000¿ø<br> ÀÚµ¿À¸·Î ¼Û±ÝµË´Ï´Ù.</body></html>");
					buyWhite = true;
				}
				
			}
		});
		settingPanel.add(whiteShapeCheckBox);
		
		jihoonShapeCheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		jihoonShapeCheckBox.setBounds(828, 60, 67, 23);
		jihoonShapeCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				whiteShapeCheckBox.setSelected(false);
				defaultShapeCheckBox.setSelected(false);
				boardLabel02.setIcon(boardImage03);
				boardLabel.setIcon(boardImage03);
				gameOp.setBoardImage(2);
				if(!buyJihoon) {
					makePanel("<html><body> ±â¾÷ 01043375460À¸·Î 300000000¿ø<br> ÀÚµ¿À¸·Î ¼Û±ÝµË´Ï´Ù.</body></html>");
					buyJihoon = true;
				}
			}
		});
		settingPanel.add(jihoonShapeCheckBox);
		
		colorLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 16));
		colorLabel.setBounds(600, 120, 75, 15);
		settingPanel.add(colorLabel);
		
		player1Label.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		player1Label.setBounds(610, 145, 75, 15);
		settingPanel.add(player1Label);
		
		player1BlackCheckBox.setSelected(true);
		player1BlackCheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		player1BlackCheckBox.setBounds(632, 170, 42, 23);
		player1BlackCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				optionP1Label.setIcon(blackStone);
				player1WhiteCheckBox.setSelected(false);
				player1BlueCheckBox.setSelected(false);
				player1YellowCheckBox.setSelected(false);
				player1Image.setIcon(blackPlayer);
				player1LatelyStone = latelyBlackStone;
				player1Stone = blackStone;
				gameOp.setPlayer1Color(0);
			}
		});
		settingPanel.add(player1BlackCheckBox);
		
		player1WhiteCheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		player1WhiteCheckBox.setBounds(700, 170, 42, 23);
		player1WhiteCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				optionP1Label.setIcon(whiteStone);
				player1BlackCheckBox.setSelected(false);
				player1BlueCheckBox.setSelected(false);
				player1YellowCheckBox.setSelected(false);
				player1Image.setIcon(whitePlayer);
				player1LatelyStone = latelyWhiteStone;
				player1Stone = whiteStone;
				gameOp.setPlayer1Color(1);
			}
		});
		settingPanel.add(player1WhiteCheckBox);
		
		player1BlueCheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		player1BlueCheckBox.setBounds(769, 170, 42, 23);
		player1BlueCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				optionP1Label.setIcon(blueStone);
				player1WhiteCheckBox.setSelected(false);
				player1BlackCheckBox.setSelected(false);
				player1YellowCheckBox.setSelected(false);
				player1Image.setIcon(bluePlayer);
				player1LatelyStone = latelyBlueStone;
				player1Stone = blueStone;
				gameOp.setPlayer1Color(2);
			}
		});
		settingPanel.add(player1BlueCheckBox);
		
		player1YellowCheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		player1YellowCheckBox.setBounds(838, 170, 42, 23);
		player1YellowCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				optionP1Label.setIcon(yellowStone);
				player1WhiteCheckBox.setSelected(false);
				player1BlueCheckBox.setSelected(false);
				player1BlackCheckBox.setSelected(false);
				player1Image.setIcon(yellowPlayer);
				player1LatelyStone = latelyYellowStone;
				player1Stone = yellowStone;
				gameOp.setPlayer1Color(3);
			}
		});
		settingPanel.add(player1YellowCheckBox);
	
		player2Label.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		player2Label.setBounds(610, 210, 75, 15);
		settingPanel.add(player2Label);
		
		player2BlackCheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		player2BlackCheckBox.setBounds(632, 235, 42, 23);
		player2BlackCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				optionP2Label.setIcon(blackStone);
				player2WhiteCheckBox.setSelected(false);
				player2BlueCheckBox.setSelected(false);
				player2YellowCheckBox.setSelected(false);
				player2Image.setIcon(blackPlayer);
				player2LatelyStone = latelyBlackStone;
				player2Stone = blackStone;
				gameOp.setPlayer2Color(0);
			}
		});
		settingPanel.add(player2BlackCheckBox);
		
		player2WhiteCheckBox.setSelected(true);
		player2WhiteCheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		player2WhiteCheckBox.setBounds(700, 235, 42, 23);
		player2WhiteCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				optionP2Label.setIcon(whiteStone);
				player2BlackCheckBox.setSelected(false);
				player2BlueCheckBox.setSelected(false);
				player2YellowCheckBox.setSelected(false);
				player2Image.setIcon(whitePlayer);
				player2LatelyStone = latelyWhiteStone;
				player2Stone = whiteStone;
				gameOp.setPlayer2Color(1);
			}
		});
		settingPanel.add(player2WhiteCheckBox);
		
		player2BlueCheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		player2BlueCheckBox.setBounds(769, 235, 42, 23);
		player2BlueCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				optionP2Label.setIcon(blueStone);
				player2WhiteCheckBox.setSelected(false);
				player2BlackCheckBox.setSelected(false);
				player2YellowCheckBox.setSelected(false);
				player2Image.setIcon(bluePlayer);
				player2LatelyStone = latelyBlueStone;
				player2Stone = blueStone;
				gameOp.setPlayer2Color(2);
			}
		});
		settingPanel.add(player2BlueCheckBox);
		
		player2YellowCheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		player2YellowCheckBox.setBounds(838, 235, 42, 23);
		player2YellowCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				optionP2Label.setIcon(yellowStone);
				player2WhiteCheckBox.setSelected(false);
				player2BlueCheckBox.setSelected(false);
				player2BlackCheckBox.setSelected(false);
				player2Image.setIcon(yellowPlayer);
				player2LatelyStone = latelyYellowStone;
				player2Stone = yellowStone;
				gameOp.setPlayer2Color(3);
			}
		});
		settingPanel.add(player2YellowCheckBox);
		
		timeLimitLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 16));
		timeLimitLabel.setBounds(600, 290, 95, 15);
		settingPanel.add(timeLimitLabel);
		
		timeShowLabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		timeShowLabel.setBounds(740, 290, 70, 15);
		settingPanel.add(timeShowLabel);
		
		timeLimitSlider.setBounds(650, 320, 200, 50);
		timeLimitSlider.setMajorTickSpacing(5);
		timeLimitSlider.setPaintTicks(true);
		timeLimitSlider.setPaintLabels(true);
		timeLimitSlider.setValue(15);
		timeLimitSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				gameOp.setTimeLimit(timeLimitSlider.getValue());
				timerLabel.setText(timeLimitSlider.getValue() + "s");
				String time = Integer.toString(timeLimitSlider.getValue()) + "s";
				timeShowLabel.setText(time);
			}
    	});
		settingPanel.add(timeLimitSlider);
		
    	
    	optionP1Label.setBounds(20, 570, 27, 27);
    	optionP1Label.setVisible(true);
    	settingPanel.add(optionP1Label);
    	
    	optionP2Label.setBounds(533, 570, 27, 27);
    	optionP2Label.setVisible(true);
    	settingPanel.add(optionP2Label);
    	
    	resetSettingButton.setBounds(600, 480, 80, 80);
    	resetSettingButton.setVisible(true);
    	resetSettingButton.setBorderPainted(false);
    	resetSettingButton.setContentAreaFilled(false);
    	resetSettingButton.setFocusPainted(false);
    	resetSettingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				resetSettingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				resetSettingButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				gameAl.reset();
				resetXStoneLabel();
			}
		});	
    	settingPanel.add(resetSettingButton);
    	
    	AILabel.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 16));
    	AILabel.setBounds(600, 390, 95, 15);
		settingPanel.add(AILabel);
		
		player1CheckBox.setSelected(true);
		player1CheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		player1CheckBox.setBounds(632, 420, 60, 23);
		player1CheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				gameAl.setFirst(true);
				player2CheckBox.setSelected(false);
			}
		});
		settingPanel.add(player1CheckBox);
		
		player2CheckBox.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 14));
		player2CheckBox.setBounds(828, 420, 67, 23);
		player2CheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				gameAl.setFirst(false);
				player1CheckBox.setSelected(false);
			}
		});
		settingPanel.add(player2CheckBox);
    	
		settingConfirmButton.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 16));
		settingConfirmButton.setBackground(Color.PINK);
		settingConfirmButton.setBounds(700, 520, 120, 40);
		settingConfirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				settingConfirmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				settingConfirmButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				makePanel("¼³Á¤ ¿Ï·á");
				settingPanel.setVisible(false);
				startPanel.setVisible(true);
				getContentPane().add(startPanel);
			}
		});	
		settingPanel.add(settingConfirmButton);
		
		settingPanel.setLayout(null);
		settingPanel.setVisible(false);
		getContentPane().setVisible(false);
		getContentPane().add(settingPanel);
	}

	public void makeStart() {
		connect6Main.setBackground(new Color(0, 0, 0));
		connect6Main.setBounds(385, 100, 190, 40);
		connect6Main.setVisible(true);
		connect6Main.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 40));
		connect6Main.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				connect6Main.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				connect6Main.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		});	
		startPanel.add(connect6Main);
		
		startMainButton.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 25));
		startMainButton.setBackground(Color.WHITE);
		startMainButton.setBorderPainted(false);
		startMainButton.setContentAreaFilled(false);
		startMainButton.setFocusPainted(false);
		startMainButton.setForeground(Color.WHITE);
		startMainButton.setBounds(395, 270, 160, 40);
		startMainButton.setVisible(true);
		startMainButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startMainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				startMainButton.setContentAreaFilled(true);
				startMainButton.setForeground(Color.BLACK);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startMainButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				startMainButton.setContentAreaFilled(false);
				startMainButton.setForeground(Color.WHITE);
			}

			@Override
			public void mousePressed(MouseEvent e) {
//				gameAl.findScore();
				
				if(!gameAl.isFirst()) {
					gameAl.launch();
					if(stoneLabel[9][9].getIcon() == xMark) {
						adjX = 8;
						adjY = 8;
					}
					else if(stoneLabel[11][11].getIcon() == xMark) {
						adjX = 12;
						adjY = 12;
					}
					else {
						adjX = 10;
						adjY = 10;
					}
					
					gameAl.setStone(adjX, adjY, 0);
					stoneLabel[adjX - 1][adjY - 1].setIcon(player1Stone);
				}
				
				startPanel.setVisible(false);
				gamePanel.setVisible(true);
				getContentPane().add(gamePanel);
			}
		});	
		startPanel.add(startMainButton);
		
		settingMainButton.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 25));
		settingMainButton.setBackground(Color.WHITE);
		settingMainButton.setBorderPainted(false);
		settingMainButton.setContentAreaFilled(false);
		settingMainButton.setFocusPainted(false);
		settingMainButton.setForeground(Color.WHITE);
		settingMainButton.setBounds(395, 370, 160, 40);
		settingMainButton.setVisible(true);
		settingMainButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				settingMainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				settingMainButton.setContentAreaFilled(true);
				settingMainButton.setForeground(Color.BLACK);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				settingMainButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				settingMainButton.setContentAreaFilled(false);
				settingMainButton.setForeground(Color.WHITE);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				startPanel.setVisible(false);
				settingPanel.setVisible(true);
				getContentPane().add(settingPanel);
			}
		});	
		startPanel.add(settingMainButton);
		
		blackStoneMain.setBackground(new Color(0, 0, 0));
		blackStoneMain.setBounds(295, 160, 360, 360);
		blackStoneMain.setVisible(true);
		blackStoneMain.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.BOLD, 40));
		startPanel.add(blackStoneMain);
		
		startPanel.setBackground(Color.WHITE);
		startPanel.setLayout(null);
		startPanel.setVisible(true);
		getContentPane().setVisible(true);
		getContentPane().add(startPanel);
	}
	
	public TimerTask timerTaskMaker() {
		TimerTask tempTask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(count > 15) {
					timerLabel.setText(Integer.toString(count) + "s");
					count--;
				}
				else if(count == 15) {
					timerLineLabel.setText(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					timerLabel.setText("15s");
					count--;
				}
				else if(count == 14) {
					timerLineLabel.setText(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					timerLabel.setText("14s");
					count--;
				}
				else if(count == 13) {
					timerLineLabel.setText(">>>>>>>>>>>>>>>>>>>>>>>>>>");
					timerLabel.setText("13s");
					count--;
				}
				else if(count == 12) {
					timerLineLabel.setText(">>>>>>>>>>>>>>>>>>>>>>>>");
					timerLabel.setText("12s");
					count--;
				}
				else if(count == 11) {
					timerLineLabel.setText(">>>>>>>>>>>>>>>>>>>>>>");
					timerLabel.setText("11s");
					count--;
				}
				else if(count == 10) {
					timerLineLabel.setText(">>>>>>>>>>>>>>>>>>>>");
					timerLabel.setText("10s");
					count--;
				}
				else if(count == 9) {
					timerLineLabel.setText(">>>>>>>>>>>>>>>>>>");
					timerLabel.setText("9s");
					count--;
				}
				else if(count == 8) {
					timerLineLabel.setText(">>>>>>>>>>>>>>>>");
					timerLabel.setText("8s");
					count--;
				}
				else if(count == 7) {
					timerLineLabel.setText(">>>>>>>>>>>>>>");
					timerLabel.setText("7s");
					count--;
				}
				else if(count == 6) {
					timerLineLabel.setText(">>>>>>>>>>>>");
					timerLabel.setText("6s");
					count--;
				}
				else if(count == 5) {
					timerLineLabel.setText(">>>>>>>>>>");
					timerLabel.setText("5s");
					count--;
				}
				else if(count == 4) {
					timerLineLabel.setText(">>>>>>>>");
					timerLabel.setText("4s");
					count--;
				}
				else if(count == 3) {
					timerLineLabel.setText(">>>>>>");
					timerLabel.setText("3s");
					count--;
				}
				else if(count == 2) {
					timerLineLabel.setText(">>>>");
					timerLabel.setText("2s");
					count--;
				}
				else if(count == 1) {
					timerLineLabel.setText(">>");
					timerLabel.setText("1s");
					count--;
				}
				else {
					timer.cancel();
					System.out.println("END!");
				}
			}
		};
		return tempTask;
	}
	
	public void makePanel(String content) {
		JFrame jf = new JFrame();
		JLabel jl = new JLabel(content);
		jf.setSize(320,160);
		jl.setFont(new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 16));
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
		jf.getContentPane().add(jl);
		jl.setVisible(true);
		jf.setVisible(true);
		jf.setLocationRelativeTo(null);
	}
	
	public void resetXStoneLabel() {
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				if(stoneLabel[i][j].getIcon() == xMark)
					stoneLabel[i][j].setIcon(null);
			}
		}
		
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				if(stoneLabel02[i][j].getIcon() == xMark)
					stoneLabel02[i][j].setIcon(null);
			}
		}
	}
	
	public void resetAllStoneLabel() {
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				if(stoneLabel[i][j].getIcon() != null)
					stoneLabel[i][j].setIcon(null);
			}
		}
		
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				if(stoneLabel02[i][j].getIcon() != null)
					stoneLabel02[i][j].setIcon(null);
			}
		}
	}

	public void resetGameStoneLabel() {
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				if(stoneLabel[i][j].getIcon() == whiteStone || stoneLabel[i][j].getIcon() == blackStone || stoneLabel[i][j].getIcon() == blueStone || stoneLabel[i][j].getIcon() == yellowStone || stoneLabel[i][j].getIcon() == latelyBlackStone || stoneLabel[i][j].getIcon() == latelyBlueStone || stoneLabel[i][j].getIcon() == latelyWhiteStone || stoneLabel[i][j].getIcon() == latelyYellowStone)
					stoneLabel[i][j].setIcon(null);
			}
		}
		
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				if(stoneLabel02[i][j].getIcon() == whiteStone || stoneLabel02[i][j].getIcon() == blackStone || stoneLabel02[i][j].getIcon() == blueStone || stoneLabel02[i][j].getIcon() == yellowStone || stoneLabel02[i][j].getIcon() == latelyBlackStone || stoneLabel02[i][j].getIcon() == latelyBlueStone || stoneLabel02[i][j].getIcon() == latelyWhiteStone || stoneLabel02[i][j].getIcon() == latelyYellowStone)
					stoneLabel02[i][j].setIcon(null);
			}
		}
	}
}
