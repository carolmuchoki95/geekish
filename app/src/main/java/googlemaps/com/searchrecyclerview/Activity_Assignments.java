package googlemaps.com.searchrecyclerview;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class Activity_Assignments extends AppCompatActivity {
    Button browse, check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        browse = (Button) findViewById(R.id.browse);
        check = (Button) findViewById(R.id.check);

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                open the file system
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto: "));
                startActivity(Intent.createChooser(emailIntent, "Continue to attach file"));


            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*open the class email*/

                Intent emailIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"));
                PackageManager pm = getPackageManager();

                List<ResolveInfo> resInfo = pm.queryIntentActivities(emailIntent, 0);
                if (resInfo.size() > 0) {
                    ResolveInfo ri = resInfo.get(0);
                    // First create an intent with only the package name of the first registered email app
                    // and build a picked based on it
                    Intent intentChooser = pm.getLaunchIntentForPackage(ri.activityInfo.packageName);
                    Intent openInChooser =
                            Intent.createChooser(intentChooser,
                                    getString(R.string.user_reg_email_client_chooser_title));

                    // Then create a list of LabeledIntent for the rest of the registered email apps

                    List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
                    for (int i = 1; i < resInfo.size(); i++) {
                        // Extract the label and repackage it in a LabeledIntent
                        ri = resInfo.get(i);
                        String packageName = ri.activityInfo.packageName;
                        Intent intent = pm.getLaunchIntentForPackage(packageName);
                        intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
                    }

                    LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
                    // Add the rest of the email apps to the picker selection
                    openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
                    startActivity(openInChooser);
                }
            }
        });
    }

}
