import java.util.Stack;

public class Player {
  private Stack<Station> backStack;
  
  public Player(Station startStation) {
    backStack = new Stack<Station>();
    backStack.push(startStation);
  }

  public Station getCurrentStation() {
    return backStack.peek();
  }

  public int getBackStackCount() {
    return backStack.size();
  }

  public void popBackStack() {
    backStack.pop();
  }

  public void pushBackStack(Station station) {
    backStack.push(station);
  }
}
