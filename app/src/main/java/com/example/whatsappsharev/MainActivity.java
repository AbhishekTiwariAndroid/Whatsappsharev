package com.example.whatsappsharev;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;

public class MainActivity extends Activity {
    private static final int PICK_VIDEO_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button chooseVideoButton = findViewById(R.id.chooseVideoButton);
        chooseVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open the file picker
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*"); // Specify the MIME type to filter video files
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                // Start the file picker activity
                startActivityForResult(intent, PICK_VIDEO_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                Uri videoUri = data.getData();

                // Now, you can send the selected video using the Share Intent to WhatsApp
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("video/*");
                shareIntent.putExtra(Intent.EXTRA_STREAM, videoUri);
                shareIntent.setPackage("com.whatsapp"); // Specify the WhatsApp package name

                startActivity(shareIntent);
            }
        }
    }
}
