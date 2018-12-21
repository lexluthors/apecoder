package com.apecoder.club.ui.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apecoder.apecoder.R;
import com.apecoder.club.app.BuildConfig;
import com.apecoder.club.base.BaseActivity;
import com.apecoder.club.util.OtherUtils;


public class WebViewActivity extends BaseActivity
{
	private WebView webView;
	private ProgressBar progress;
	private TextView mTitle;
	private String url;
	private Toolbar toolbar;

	public static void intentStart(Context context,String title, String url) {
		Intent intent = new Intent(context, WebViewActivity.class);
		intent.putExtra("url",url);
		intent.putExtra("title",title);
		context.startActivity(intent);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack())
		{
			webView.goBack();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_webview_layout;
	}

	@Override
	public void initView() {
		webView = (WebView) findViewById(R.id.webView);
		progress = (ProgressBar) findViewById(R.id.progressBar);
		mTitle = (TextView) findViewById(R.id.title);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
//		toolbar.setNavigationIcon(R.drawable.ic_webview_finish);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		toolbar.setNavigationOnClickListener(v -> finish());
		mTitle.setText(getIntent().getStringExtra("title"));
	}

	@Override
	public void initData() {
		url = getIntent().getStringExtra("url");
		setUpWebviewAndLoadByUrl();
	}

	@Override
	protected void onDestroy()
	{
		webView.stopLoading();
		webView.destroy();
		super.onDestroy();
	}

	@SuppressWarnings("deprecation")
	@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
	protected void setUpWebviewAndLoadByUrl()
	{
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		if (Build.VERSION.SDK_INT >= 8)
			webView.getSettings().setPluginState(WebSettings.PluginState.ON);
		webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		webView.getSettings().setAllowFileAccess(true);
		webView.getSettings().setDefaultTextEncodingName("UTF-8");
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setUserAgentString(BuildConfig.WEBVIEW_FLAG);
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//		webView.addJavascriptInterface(new JSInterface(WebViewActivity.this), "zintao");
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
		{
			webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
		}
		webView.requestFocus();
		webView.setWebViewClient(new WebViewClient()
		{
			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
			{
				// progressBar.setVisibility(View.GONE);
				progress.setVisibility(View.GONE);
				super.onReceivedError(view, errorCode, description, failingUrl);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon)
			{
				// progressBar.setVisibility(View.VISIBLE);
				progress.setVisibility(View.VISIBLE);
				super.onPageStarted(view, url, favicon);
			}

			@RequiresApi(api = Build.VERSION_CODES.KITKAT)
			@Override
			public void onPageFinished(WebView view, final String url)
			{
				// progressBar.setVisibility(View.GONE);
				super.onPageFinished(view, url);
				progress.setVisibility(View.GONE);
			}

			@Override
			public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
			{
				handler.proceed();// 接受证书
				// progressBar.setVisibility(View.GONE);
				progress.setVisibility(View.GONE);
			}
		});
		webView.setWebChromeClient(new WebChromeClient()
		{
			@Override
			public void onProgressChanged(WebView view, int newProgress)
			{
				progress.setVisibility(View.VISIBLE);
				progress.setProgress(newProgress);
			}

			@Override
			public boolean onJsAlert(WebView view, String url, String message, JsResult result)
			{
				return super.onJsAlert(view, url, message, result);
			}

			@Override
			public void onReceivedTitle(WebView view, String title)
			{
				super.onReceivedTitle(view, title);
			}

		});
		webView.loadUrl(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_webview,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case android.R.id.home:
				finish();
				break;
			case R.id.menu_share:
				Intent localIntent = new Intent("android.intent.action.SEND");
				localIntent.setType("text/plain");
				localIntent.putExtra("android.intent.extra.TEXT", url);
				localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(localIntent);
				break;
			case R.id.menu_copy_link:
				if (OtherUtils.copyText(this,url)) {
					Snackbar.make(webView, "链接复制成功", Snackbar.LENGTH_LONG).show();
				}
				break;
			case R.id.menu_open_with:
				OtherUtils.openWithBrowser(this, url);
				break;
		}
		return true;
	}

}
