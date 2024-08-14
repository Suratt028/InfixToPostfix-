import java.util.Scanner;


class InfixToPostfix {


    // Node class for the linked list based stack
    static class Node {
        char data;
        Node next;


        public Node(char data) {
            this.data = data;
            this.next = null;
        }
    }


    // Stack class implemented using a linked list
    static class Stack {
        Node top;


        public Stack() {
            this.top = null;
        }


        public boolean isEmpty() {
            return top == null;
        }


        public void push(char data) {
            Node newNode = new Node(data);
            newNode.next = top;
            top = newNode;
        }


        public char pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return '\0';
            }
            char popped = top.data;
            top = top.next;
            return popped;
        }


        public char peek() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return '\0';
            }
            return top.data;
        }
    }


    // Function to assign precedence to operators
    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }


    // Function to convert infix expression to postfix notation
    public static String infixToPostfix(String exp) {
        String result = "";
        Stack stack = new Stack();


        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);


            if (Character.isLetterOrDigit(c)) {
                result += c;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    return "Invalid Expression";
                } else {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    if (stack.peek() == '(') {
                        return "Invalid Expression";
                    }
                    result += stack.pop();
                }
                stack.push(c);
            }
        }


        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid Expression";
            }
            result += stack.pop();
        }
        return result;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object


        System.out.print("Enter an infix expression: ");
        String exp = scanner.nextLine(); // Read input from user


        System.out.println("Postfix expression: " + infixToPostfix(exp));
    }
}



