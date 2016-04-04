package com.tixon.timemanagement.databinding;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.BR;
import android.view.View;
public class TaskItemBinding extends android.databinding.ViewDataBinding {
    
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    private final android.widget.LinearLayout mboundView0;
    public final android.widget.TextView taskTitle;
    // variables
    private com.tixon.timemanagement.task.Task mTask;
    // values
    // listeners
    
    public TaskItemBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.taskTitle = (android.widget.TextView) bindings[1];
        this.taskTitle.setTag(null);
        setRootTag(root);
        invalidateAll();
    }
    
    @Override
    public void invalidateAll() {
        synchronized(this) {
            mDirtyFlags = 0x2L;
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
            case BR.task :
                setTask((com.tixon.timemanagement.task.Task) variable);
                return true;
        }
        return false;
    }
    
    public void setTask(com.tixon.timemanagement.task.Task task) {
        this.mTask = task;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        super.requestRebind();
    }
    public com.tixon.timemanagement.task.Task getTask() {
        return mTask;
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
        com.tixon.timemanagement.task.Task task = mTask;
        java.lang.String titleTask = null;
    
        if ((dirtyFlags & 0x3L) != 0) {
            // read task~
            task = task;
        
            if (task != null) {
                // read title~.~task~
                titleTask = task.getTitle();
            }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1
            this.taskTitle.setText(titleTask);
        }
    }
    // Listener Stub Implementations
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    
    public static TaskItemBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static TaskItemBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<TaskItemBinding>inflate(inflater, com.tixon.timemanagement.R.layout.task_item, root, attachToRoot, bindingComponent);
    }
    public static TaskItemBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static TaskItemBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.tixon.timemanagement.R.layout.task_item, null, false), bindingComponent);
    }
    public static TaskItemBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static TaskItemBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/task_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new TaskItemBinding(bindingComponent, view);
    }
}
    /* flag mapping
        flag 0: task~
        flag 1: INVALIDATE ANY
    flag mapping end*/
    //end