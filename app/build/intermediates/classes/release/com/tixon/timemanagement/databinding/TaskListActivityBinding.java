package com.tixon.timemanagement.databinding;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.BR;
import android.view.View;
public class TaskListActivityBinding extends android.databinding.ViewDataBinding {
    
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.taskListAppBarLayout, 1);
        sViewsWithIds.put(R.id.toolbarTaskList, 2);
        sViewsWithIds.put(R.id.taskListRecyclerView, 3);
    }
    // views
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    public final android.support.design.widget.AppBarLayout taskListAppBarLayout;
    public final android.support.v7.widget.RecyclerView taskListRecyclerView;
    public final android.support.v7.widget.Toolbar toolbarTaskList;
    // variables
    // values
    // listeners
    
    public TaskListActivityBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.taskListAppBarLayout = (android.support.design.widget.AppBarLayout) bindings[1];
        this.taskListRecyclerView = (android.support.v7.widget.RecyclerView) bindings[3];
        this.toolbarTaskList = (android.support.v7.widget.Toolbar) bindings[2];
        setRootTag(root);
        invalidateAll();
    }
    
    @Override
    public void invalidateAll() {
        synchronized(this) {
            mDirtyFlags = 0x1L;
        }
        requestRebind();
    }
    
    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
        }
        return false;
    }
    
    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }
    
    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    
    public static TaskListActivityBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static TaskListActivityBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<TaskListActivityBinding>inflate(inflater, com.tixon.timemanagement.R.layout.task_list_activity, root, attachToRoot, bindingComponent);
    }
    public static TaskListActivityBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static TaskListActivityBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.tixon.timemanagement.R.layout.task_list_activity, null, false), bindingComponent);
    }
    public static TaskListActivityBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static TaskListActivityBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/task_list_activity_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new TaskListActivityBinding(bindingComponent, view);
    }
}
    /* flag mapping
        flag 0: INVALIDATE ANY
    flag mapping end*/
    //end