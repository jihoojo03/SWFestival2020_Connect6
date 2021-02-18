# SWFestival2020_Connect6


<div>
소프트웨어 중심단 한동 SW 페스티벌의 일환으로 진행된 육목대회의 알고리즘 및 코드.
</div>

<h2> 알고리즘 </h2>

<div>
  육목은 기본적으로 한 명의 선수가 돌을 연속으로 6개 놓았을 때 이기는 게임으로, 이를 구현하기 위한 여러가지 방법이 있지만 바둑판의 모든 점에 대한 가중치를 계산하는 방식을 이용하여 프로그램을 작성하였다.
기본적인 착수 과정은
1.	자기 턴에서 6개의 돌로 승리할 수 있는가?
2.	상대방의 돌이 한쪽 이상 뚫려 있는 4개의 돌을 가지고 있는가?
3.	최적의 공격점을 찾는다.
순으로 진행되고 위의 순서는 알고리즘의 우선순위와 같다. 이러한 착수 과정을 진행하기 위해 바둑판의 모든 점에 대한 가중치가 필요하며, 우선순위에 따라 가중치를 계산하여 해당 위치에 돌을 착수한다.
바둑판의 모든 점의 가중치를 계산하는 방법은 한 점에 대하여 비어 있는 각 자리에 대해 돌을 놓았을 때의 바둑판의 상황에 따른 가중치를 계산하는 것이다. 바둑판의 상황이란 한 색깔의 돌에 대한 가로, 세로, 좌 대각선, 우 대각선의 돌의 개수, 이 돌들이 얼마나 비어 있는지에 대한 여부, 자신의 턴 중에서 몇 번째 돌인가에 대한 여부 등을 검사하는 것이다.
위의 과정을 통해 AI는 육목을 만들기 위한 최적의 공격점을 찾으며, 상대방의 공격에 대한 유연한 방어점 또한 찾아낸다.
 </div>
 
<h2> 소스코드 설명 </h2>

<h5>a. Connect6AIMain.java</h5>
-	GUITest()를 실행하는 메인 클래스이다. 

<h5>b. GUITest.java</h5>
-	public void makeGame()
게임을 진행하는 함수로 게임 진행 횟수에 따라 착수를 할 수 있도록 하고 승리 여부를 체크한다.

-	public void makeSetting()
돌의 색과 진행 순서 등을 설정하는 함수이다.

-	public void makeStart()
AI가 흑일 경우 먼저 착수하며 게임을 시작하도록 하는 함수이다.

-	public TimerTask timerTaskMaker()
착수 시간을 제한하기 위한 함수이다. 

-	public void makePanel(String content)
알림창을 띄우는 함수이다.

-	public void resetXStoneLabel() / public void resetXStoneLabel() / public void resetXStoneLabel()
게임을 리셋할 경우 경기 화면을 초기화 하기 위한 함수이다.

<h5>c. Game Algorithm.java</h5>
-	public int win(int x, int y) / public int winCount(int x, int y, int direction)
현재 턴에서 승리 여부를 확인해주는 함수이다.

-	public double connect6ShapeScore(int consecutive, int openEnds, int currentTurn)
각 위치의 가중치를 계산하는 함수로 연속으로 놓여진 돌의 개수와 그 연속된 돌들의 양쪽 끝에 비어 있는 자리가 있는지에 따라 가중치를 다르게 판단한다.

-	public double analyzeHorizontalSets(int current_turn)
가로로 움직이면서 각 위치의 가중치를 계산한다. 즉, 가로를 관점으로 연속된 돌들의 개수와 양쪽 끝에 비어있는 자리들을 확인하며 가중치를 계산한다.

-	public double analyzeVerticalSets (int current_turn)
세로로 움직이면서 각 위치의 가중치를 계산한다. 즉, 세로를 관점으로 연속된 돌들의 개수와 양쪽 끝에 비어있는 자리들을 확인하며 가중치를 계산한다.

-	public double analyzeLeftDiagonalSets (int current_turn)
왼쪽 위에서 오른쪽 아래로 움직이면서 각 위치의 가중치를 계산한다. 즉, 좌 대각선을 관점으로 연속된 돌들의 개수와 양쪽 끝에 비어 있는 자리들을 확인하며 가중치를 계산한다.

-	public double analyzeRightDiagonalSets (int current_turn)
오른쪽 위에서 왼쪽 아래로 움직이면서 각 위치의 가중치를 계산한다. 즉, 우 대각선을 관점으로 연속된 돌들의 개수와 양쪽 끝에 비어 있는 자리들을 확인하며 가중치를 계산한다.

-	public boolean findConnect6Move()
현재 돌을 놓아 6개의 연속된 돌들을 만들어낼 수 있는지 확인하는 함수이다.

-	public boolean findDefenceMove()
상대방이 돌을 놓아 6개의 연속된 돌들을 만들어낼 수 있는지 확인하는 함수이다.

-	public boolean findAttackMove()
공격을 개시하게 하는 함수이다.




<h5>d. Gameoption.java</h5>
	게임 화면, 돌의 색상, 시간제한을 설정하는 클래스이다.

</div>
