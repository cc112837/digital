package com.cc.digital.utils;

import android.content.Context;
import android.view.ActionProvider;
import android.view.View;

public class PlusActionProvider extends ActionProvider {

	private Context context;

	public PlusActionProvider(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public View onCreateActionView() {
		return null;
	}
	@Override
	public boolean hasSubMenu() {
		return true;
	}

}