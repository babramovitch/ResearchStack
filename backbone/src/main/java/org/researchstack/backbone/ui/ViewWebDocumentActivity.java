package org.researchstack.backbone.ui;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MenuItem;

import org.researchstack.backbone.ui.views.LocalWebView;
import org.researchstack.backbone.utils.ResUtils;

public class ViewWebDocumentActivity extends PinCodeActivity
{

    public static final String TAG          = ViewWebDocumentActivity.class.getSimpleName();
    public static final String KEY_DOC_NAME = TAG + ".DOC_NAME";
    public static final String KEY_TITLE    = TAG + ".TITLE";

    public static Intent newIntent(Context context, String title, String docName)
    {
        Intent intent = new Intent(context, ViewWebDocumentActivity.class);
        intent.putExtra(KEY_DOC_NAME, docName);
        intent.putExtra(KEY_TITLE, title);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        String documentName = getIntent().getStringExtra(KEY_DOC_NAME);
        String path = ResUtils.getHTMLFilePath(documentName);

        LocalWebView webView = new LocalWebView(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String title = getIntent().getStringExtra(KEY_TITLE);
        if(! TextUtils.isEmpty(title))
        {
            actionBar.setTitle(title);
        }
        else
        {
            webView.setCallbacks(docTitle -> {
                actionBar.setTitle(title);
            });
        }
        webView.loadUrl(path);
        setContentView(webView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}