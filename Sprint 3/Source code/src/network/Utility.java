package network;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.mail.handlers.message_rfc822;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import modele.Deck;
import modele.Game;
import util.Serialisation;
import view.GameDualPane;
import view.ConfigGameLocal;
import view.MainFx;
import view.ConfigGame;
import network.Message;

public class Utility {
	public static ConfigGameLocal localOption = MainFx.getStackGamePane().getConfigGame().getLocalption();
	public static Message message;
	public static void setNameOpponent(String str) {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						localOption.getLbVarOpponent().setText(str);
						localOption.getLbVarOpponent().setTextFill(Color.LAWNGREEN);
					}

				});
				return null;
			}
		};
		new Thread(task).start();
	}

	public static void setStartEnable() {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MainFx.getStackGamePane().getConfigGame().getBtnStart().setDisable(false);
					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}

	public static void startGame() {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MainFx.getStackGamePane().getChildren().add(MainFx.getStackGamePane().newDualPane());
						MainFx.getStackGamePane().getConfigGame().setVisible(false);

					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}

	public static void setWheel(int nb) {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MainFx.getStackGamePane().getGameDualPane().getWheelDualPane().rotationWheel(nb);
						MainFx.getStackGamePane().getGameDualPane().getWheelDualPane().getCircleSpin().setDisable(true);
					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}

	public static void visibleFieldOpponent(String time) {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						;
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getTxtFieldAnswerJ2().setVisible(true);
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getLabelTimerP2().setText(time);
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getLabelTimerP2().setVisible(true);
					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}

	public static void visibleFieldMe(String time) {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						;
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getTxtFieldAnswerJ1().setVisible(true);
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getTxtFieldAnswerJ1().requestFocus();
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getLabelTimerP1().setText(time);
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getLabelTimerP1().setVisible(true);
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getBtnConfirm().setVisible(true);;
					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}
	public static void correctOpponent(String answer) {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getTxtFieldAnswerJ2().setText(answer);
						MainFx.getStackGamePane().getGameDualPane().setCheesesPane(Game.getInstanceGame().getCurrentQuestion(), 1, true);
						MainFx.getStackGamePane().getAlertInformation(Game.getInstanceGame().getPlayers().get(1).getName()+ " 's answer is correct").showAndWait();
						PlayerClient.getInstanceClient().sendMessage(Action.READY, null);
					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}

	public static void correctMe() {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MainFx.getStackGamePane().getAlertInformation("Your answer is correct").showAndWait();
						MainFx.getStackGamePane().getGameDualPane().setCheesesPane(Game.getInstanceGame().getCurrentQuestion(), 0, true);
						PlayerClient.getInstanceClient().sendMessage(Action.READY, null);
					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}

	public static void wrongOpponent(String answer) {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getTxtFieldAnswerJ2().setText(answer);
					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}
	public static void wrongMe() {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getTxtFieldAnswerJ1().setDisable(true);
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getBtnConfirm().setDisable(true);
						MainFx.getStackGamePane().getAlertInformation("Your answer is wrong").showAndWait();
						PlayerClient.getInstanceClient().sendMessage(Action.READY, null);

					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}
	public static void nextLaps() {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getTxtFieldAnswerJ1().setVisible(false);
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getTxtFieldAnswerJ1().setDisable(false);
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getTxtFieldAnswerJ1().clear();
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getLabelTimerP1().setVisible(false);							

						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getTxtFieldAnswerJ2().setVisible(false);
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getTxtFieldAnswerJ2().clear();
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getLabelTimerP2().setVisible(false);

						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getBtnConfirm().setVisible(false);
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getBtnConfirm().setDisable(false);

						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().setVisible(false);
						MainFx.getStackGamePane().getGameDualPane().getWheelDualPane().setVisible(true);
						MainFx.getStackGamePane().getGameDualPane().getWheelDualPane().getCircleSpin().setDisable(false);
					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}
	public static void refreshTimerQuestion(String time) {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getLabelTimerQuestion().setText(time);
					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}
	public static void timePlayerOut() {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String answer = MainFx.getStackGamePane().getGameDualPane().getQuestionLocalPane().getTxtFieldAnswerJ1().getText();
						PlayerClient.getInstanceClient().sendMessage(Action.ANSWER, answer);
					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}
	public static void timerQuestionOut() {
		Task<Void> task = new Task<Void>() {
			protected Void call() throws Exception {    	
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MainFx.getStackGamePane().getAlertInformation("Time out").show();
						PlayerClient.getInstanceClient().sendMessage(Action.READY, null);
						nextLaps();
					}

				});
				return null;

			}
		};
		new Thread(task).start();
	}
}
