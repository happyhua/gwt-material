package gwt.material.design.client.async.mixin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.async.AsyncWidgetCallback;
import gwt.material.design.client.async.IsAsyncWidget;
import gwt.material.design.client.async.loader.AsyncDisplayLoader;
import gwt.material.design.client.async.loader.DefaultDisplayLoader;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.CssName;

public class AsyncWidgetMixin<W extends Widget, V> implements IsAsyncWidget<W, V> {


    protected W widget;
    protected AsyncDisplayLoader<V> displayLoader;
    protected AsyncWidgetCallback asyncCallback;

    private ToggleStyleMixin<MaterialWidget> asyncStyleMixin;

    public AsyncWidgetMixin(W widget) {
        this.widget = widget;
    }

    @Override
    public void load(AsyncWidgetCallback<W, V> asyncCallback) {
        if (displayLoader == null) {
            displayLoader = new DefaultDisplayLoader();
            GWT.log("Will be using the default display loader for asynchronous feature.");
        }

        displayLoader.loading();
        asyncCallback.load(new AsyncCallback<V>() {
            @Override
            public void onFailure(Throwable caught) {
                displayLoader.failure(caught.getMessage());
                displayLoader.finalize();
            }

            @Override
            public void onSuccess(V result) {
                displayLoader.success(result);
                displayLoader.finalize();
            }
        }, widget);
    }

    @Override
    public void setAsynchronous(boolean asynchronous) {
        getAsyncStyleMixin().setOn(asynchronous);
    }

    @Override
    public boolean isAsynchronous() {
        return getAsyncStyleMixin().isOn();
    }

    @Override
    public void setAsyncCallback(AsyncWidgetCallback asyncCallback) {
        this.asyncCallback = asyncCallback;
    }

    @Override
    public AsyncWidgetCallback getAsyncCallback() {
        return asyncCallback;
    }

    @Override
    public void setAsyncDisplayLoader(AsyncDisplayLoader displayLoader) {
        this.displayLoader = displayLoader;
    }

    @Override
    public AsyncDisplayLoader getAsyncDisplayLoader() {
        return displayLoader;
    }

    public ToggleStyleMixin<MaterialWidget> getAsyncStyleMixin() {
        if (asyncStyleMixin == null) {
            asyncStyleMixin = new ToggleStyleMixin<>(new MaterialWidget(widget.getElement()), CssName.ASYNC);
        }
        return asyncStyleMixin;
    }
}