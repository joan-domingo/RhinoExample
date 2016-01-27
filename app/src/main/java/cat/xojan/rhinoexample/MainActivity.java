package cat.xojan.rhinoexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String JAVASCRIPT_FILE = "javascript.js";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textview);

        runJavaScript();
    }

    private void runJavaScript() {
        // Create a Sale object to evaluate.
        Sale sale = new Sale();

        // Define evaluation JavaScript.
        String script = loadScriptFromAssets(JAVASCRIPT_FILE);

        // Create and enter a Context.
        // A Context stores information about the execution environment of a script.
        Context cx = Context.enter();

        //By default, rhino will try do optimization by generating JVM bytecode on the fly.
        // Android doesn't run JVM bytecode byt Dalvik bytecode.
        // That is why we have to disable optimization:
        cx.setOptimizationLevel(-1);

        try {
            // Initialize the standard objects (Object, Function, etc.).
            // This must be done before scripts can be executed.
            // The null parameter tells initStandardObjects
            // to create and return a scope object that we use
            // in later calls.
            Scriptable scope = cx.initStandardObjects();

            Scriptable that = cx.newObject(scope);

            // Execute the script
            Function fct = cx.compileFunction(scope, script, "TestScript", 1, null);
            Object result = fct.call(cx, scope, that, new Object[] {sale});

            //print result
            String resultString = (String) Context.jsToJava(result, String.class);
            mTextView.setText(resultString);

        } catch( Exception e ) {
            e.printStackTrace();
        }
        finally {
            // Exit the Context. This removes the association between the Context and the current thread and is an
            // essential cleanup action. There should be a call to exit for every call to enter.
            Context.exit();
        }
    }

    /**
     * Open and read a javascript file from the assets folder.
     * @return the file content as a string.
     */
    private String loadScriptFromAssets(String fileName) {
        StringBuilder script = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName)));

            // do reading, usually loop until end of file reading
            String line;
            while ((line = reader.readLine()) != null) {
                //process line
                script.append(line);
            }
        } catch (IOException e) {
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    return null;
                }
            }
        }
        return script.toString();
    }
}
