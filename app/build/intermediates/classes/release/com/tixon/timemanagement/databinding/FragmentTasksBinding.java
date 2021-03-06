package com.tixon.timemanagement.databinding;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.BR;
import android.view.View;
public class FragmentTasksBinding extends android.databinding.ViewDataBinding {
    
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.taskRecyclerView, 1);
        sViewsWithIds.put(R.id.whiteFrame, 2);
        sViewsWithIds.put(R.id.clickView, 3);
        sViewsWithIds.put(R.id.imageTaskCreated, 4);
    }
    // views
    public final android.widget.FrameLayout clickView;
    public final android.widget.ImageView imageTaskCreated;
    private final android.widget.RelativeLayout mboundView0;
    public final android.support.v7.widget.RecyclerView taskRecyclerView;
    public final android.widget.FrameLayout whiteFrame;
    // variables
    // values
    // listeners
    
    public FragmentTasksBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.clickView = (android.widget.FrameLayout) bindings[3];
        this.imageTaskCreated = (android.widget.ImageView) bindings[4];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.taskRecyclerView = (android.support.v7.widget.RecyclerView) bindings[1];
        this.whiteFrame = (android.widget.FrameLayout) bindings[2];
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
    
    public static FragmentTasksBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentTasksBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentTasksBinding>inflate(inflater, com.tixon.timemanagement.R.layout.fragment_tasks, root, attachToRoot, bindingComponent);
    }
    public static FragmentTasksBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentTasksBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.tixon.timemanagement.R.layout.fragment_tasks, null, false), bindingComponent);
    }
    public static FragmentTasksBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentTasksBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_tasks_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentTasksBinding(bindingComponent, view);
    }
}
    /* flag mapping
        flag 0: INVALIDATE ANY
    flag mapping end*/
    //end