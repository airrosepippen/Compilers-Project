import java.util.*;

import com.sun.java_cup.internal.runtime.Symbol;

import java.io.*;
import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the Scanner.
 * This version is set up to test all tokens, but more code is needed to test 
 * other aspects of the scanner (e.g., input that causes errors, character 
 * numbers, values associated with tokens)
 */
public class P2 {
    public static void main(String[] args) throws IOException {
                                          
        // test all tokens
    	System.out.println("testAllTokens: Now running allTokens.in...");
        testAllTokens();
        CharNum.num = 1;
    
        // test all tokens other (other testing of tokens to run other files)
        System.out.println("testAllTokensOther: Now running eof.in...");
        testAllTokensOther("eof", false);
        CharNum.num = 1;
        
        System.out.println("testAllTokensOther: Running simpleTest.in");
        testAllTokensOther("simpleTest", false);
        CharNum.num = 1;
        
        
        
     // ADD CALLS TO OTHER TEST METHODS HERE
    }
    
    /**
     * testAllTokensOther
     *
     * Open and read from file with the formate file_name.in
     * For each token read, write the corresponding string to file_name.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     * Includes a boolean field to check cases with invalid or valid examples.
     */
    private static void testAllTokensOther(String fname, boolean isLineValid) throws IOException{
    	// open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        
        try {
            inFile = new FileReader(fname + ".in");
            outFile = new PrintWriter(new FileWriter(fname + ".out"));
            
            System.setErr(new PrintStream(new FileOutputStream(fname + ".err")));
            
        } catch (FileNotFoundException e) {
            System.err.println("File " + fname + ".in not found!");
            System.exit(-1);
        } catch (IOException e) {
            System.err.println(fname + ".out cannot be opened!");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        
        while(token.sym != sym.EOF) {
        	if(isLineValid) {
        		outFile.print("(" + ((TokenVal)token.value).linenum + "," + ((TokenVal)token.value).charnum + "):");
        	}
        	
        	switch(token.sym) {
        	
        	case sym.BOOL:
                outFile.println("bool"); 
                break;
                
			case sym.INT:
                outFile.println("int");
                break;
                
            case sym.VOID:
                outFile.println("void");
                break;
                
            case sym.TRUE:
                outFile.println("true"); 
                break;
                
            case sym.FALSE:
                outFile.println("false"); 
                break;
                
            case sym.STRUCT:
                outFile.println("struct"); 
                break;
                
            case sym.CIN:
                outFile.println("cin"); 
                break;
                
            case sym.COUT:
                outFile.println("cout");
                break;	
                
            case sym.IF:
                outFile.println("if");
                break;
                
            case sym.ELSE:
                outFile.println("else");
                break;
                
            case sym.WHILE:
                outFile.println("while");
                break;
                
            case sym.RETURN:
                outFile.println("return");
                break;
                
            case sym.ID:
			    if (isLineValid) {
			       outFile.print("[ID]:");
			    }
                outFile.println(((IdTokenVal)token.value).idVal);
                break;
            case sym.INTLITERAL:
                if (isLineValid) {
			       outFile.print("[INTLIT]:");
		        }  
                outFile.println(((IntLitTokenVal)token.value).intVal);
                break;
                
            case sym.STRINGLITERAL: 
                if (isLineValid) {
			       outFile.print("[STRLIT]:");
		        }
                outFile.println(((StrLitTokenVal)token.value).strVal);
                break;  
                
            case sym.LCURLY:
                outFile.println("{");
                break;
                
            case sym.RCURLY:
                outFile.println("}");
                break;
                
            case sym.LPAREN:
                outFile.println("(");
                break;
                
            case sym.RPAREN:
                outFile.println(")");
                break;
                
            case sym.SEMICOLON:
                outFile.println(";");
                break;
                
            case sym.COMMA:
                outFile.println(",");
                break;
                
            case sym.DOT:
                outFile.println(".");
                break;
                
            case sym.WRITE:
                outFile.println("<<");
                break;
                
            case sym.READ:
                outFile.println(">>");
                break;	
                
            case sym.PLUSPLUS:
                outFile.println("++");
                break;
                
            case sym.MINUSMINUS:
                outFile.println("--");
                break;	
                
            case sym.PLUS:
                outFile.println("+");
                break;
                
            case sym.MINUS:
                outFile.println("-");
                break;
                
            case sym.TIMES:
                outFile.println("*");
                break;
                
            case sym.DIVIDE:
                outFile.println("/");
                break;
                
            case sym.NOT:
                outFile.println("!");
                break;
                
            case sym.AND:
                outFile.println("&&");
                break;
                
            case sym.OR:
                outFile.println("||");
                break;
                
            case sym.EQUALS:
                outFile.println("==");
                break;
                
            case sym.NOTEQUALS:
                outFile.println("!=");
                break;
                
            case sym.LESS:
                outFile.println("<");
                break;
                
            case sym.GREATER:
                outFile.println(">");
                break;
                
            case sym.LESSEQ:
                outFile.println("<=");
                break;
                
            case sym.GREATEREQ:
                outFile.println(">=");
                break;
                
			case sym.ASSIGN:
                outFile.println("=");
                break;
                
			default:
				outFile.println("UNKNOWN TOKEN");
        	}
        	token = scanner.next_token();
        }
        outFile.close();
    }
    
 
    /**
     * testAllTokens
     *
     * Open and read from file allTokens.txt
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     */
    private static void testAllTokens() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("allTokens.in");
            outFile = new PrintWriter(new FileWriter("allTokens.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.BOOL:
                outFile.println("bool"); 
                break;
			case sym.INT:
                outFile.println("int");
                break;
            case sym.VOID:
                outFile.println("void");
                break;
            case sym.TRUE:
                outFile.println("true"); 
                break;
            case sym.FALSE:
                outFile.println("false"); 
                break;
            case sym.STRUCT:
                outFile.println("struct"); 
                break;
            case sym.CIN:
                outFile.println("cin"); 
                break;
            case sym.COUT:
                outFile.println("cout");
                break;				
            case sym.IF:
                outFile.println("if");
                break;
            case sym.ELSE:
                outFile.println("else");
                break;
            case sym.WHILE:
                outFile.println("while");
                break;
            case sym.RETURN:
                outFile.println("return");
                break;
            case sym.ID:
                outFile.println(((IdTokenVal)token.value).idVal);
                break;
            case sym.INTLITERAL:  
                outFile.println(((IntLitTokenVal)token.value).intVal);
                break;
            case sym.STRINGLITERAL: 
                outFile.println(((StrLitTokenVal)token.value).strVal);
                break;    
            case sym.LCURLY:
                outFile.println("{");
                break;
            case sym.RCURLY:
                outFile.println("}");
                break;
            case sym.LPAREN:
                outFile.println("(");
                break;
            case sym.RPAREN:
                outFile.println(")");
                break;
            case sym.SEMICOLON:
                outFile.println(";");
                break;
            case sym.COMMA:
                outFile.println(",");
                break;
            case sym.DOT:
                outFile.println(".");
                break;
            case sym.WRITE:
                outFile.println("<<");
                break;
            case sym.READ:
                outFile.println(">>");
                break;				
            case sym.PLUSPLUS:
                outFile.println("++");
                break;
            case sym.MINUSMINUS:
                outFile.println("--");
                break;	
            case sym.PLUS:
                outFile.println("+");
                break;
            case sym.MINUS:
                outFile.println("-");
                break;
            case sym.TIMES:
                outFile.println("*");
                break;
            case sym.DIVIDE:
                outFile.println("/");
                break;
            case sym.NOT:
                outFile.println("!");
                break;
            case sym.AND:
                outFile.println("&&");
                break;
            case sym.OR:
                outFile.println("||");
                break;
            case sym.EQUALS:
                outFile.println("==");
                break;
            case sym.NOTEQUALS:
                outFile.println("!=");
                break;
            case sym.LESS:
                outFile.println("<");
                break;
            case sym.GREATER:
                outFile.println(">");
                break;
            case sym.LESSEQ:
                outFile.println("<=");
                break;
            case sym.GREATEREQ:
                outFile.println(">=");
                break;
			case sym.ASSIGN:
                outFile.println("=");
                break;
			default:
				outFile.println("UNKNOWN TOKEN");
            } // end switch

            token = scanner.next_token();
        } // end while
        outFile.close();
    }
}
