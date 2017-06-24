package minifsharp.strategies;

import java.io.File;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

public class strategy_run_cil_0_1 extends CompilerStrategy {
    public static strategy_run_cil_0_1 instance = new strategy_run_cil_0_1();

    /**
     * Generate and run .exe file from the .cil file
     */
	@Override
	protected void runStrategy(Context context, IStrategoTerm current, IStrategoTerm path) {
		
		// If .exe file exists remove .exe file
        if (new File(getPathExe(path)).exists()) {
        	runCommand("rm " + getPathExe(path));
        }
    	
		// Compile .cil to .exe
		runCommand("ilasm " + getPathCil(path));
		
        if (!new File(getPathExe(path)).canExecute()) {
        	// Set right permissions
        	runCommand("chmod +x " + getPathExe(path));
        }
        
        // Run the compiled .exe file
    	runCommandVerbose(getPathExe(path));
	}
}