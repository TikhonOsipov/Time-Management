package com.tixon.timemanagement.databinding;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.BR;
import android.view.View;
public class TaskListCategorizedItemBinding extends android.databinding.ViewDataBinding {
    
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.selection, 3);
    }
    // views
    private final android.widget.RelativeLayout mboundView0;
    public final android.widget.FrameLayout selection;
    public final android.widget.TextView taskListItemDescription;
    public final android.view.View taskListItemDivider;
    public final android.widget.TextView taskListItemTitle;
    // variables
    private com.tixon.timemanagement.task.Task mTask;
    // values
    // listeners
    
    public TaskListCategorizedItemBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.selection = (android.widget.FrameLayout) bindings[3];
        this.taskListItemDescription = (android.widget.TextView) bindings[2];
        this.taskListItemDescription.setTag(null);
        this.taskListItemDivider = (android.view.View) bindings[0];
        this.taskListItemDivider.setTag(null);
        this.taskListItemTitle = (android.widget.TextView) bindings[1];
        this.taskListItemTitle.setTag(null);
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
        java.lang.String descriptionTask = null;
        java.lang.String titleTask = null;
        com.tixon.timemanagement.task.Task task = mTask;
    
        if ((dirtyFlags & 0x3L) != 0) {
            // read task~
            task = task;
        
            if (task != null) {
                // read description~.~task~
                descriptionTask = task.getDescription();
                // read title~.~task~
                titleTask = task.getTitle();
            }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1
            this.taskListItemDescription.setText(descriptionTask);
            this.taskListItemTitle.setText(titleTask);
        }
    }
    // Listener Stub Implementations
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    
    public static TaskListCategorizedItemBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static TaskListCategorizedItemBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<TaskListCategorizedItemBinding>inflate(inflater, com.tixon.timemanagement.R.layout.task_list_categorized_item, root, attachToRoot, bindingComponent);
    }
    public static TaskListCategorizedItemBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static TaskListCategorizedItemBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.tixon.timemanagement.R.layout.task_list_categorized_item, null, false), bindingComponent);
    }
    public static TaskListCategorizedItemBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static TaskListCategorizedItemBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/task_list_categorized_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new TaskListCategorizedItemBinding(bindingComponent, view);
    }
}
    /* flag mapping
        flag 0: task~
        flag 1: INVALIDATE ANY
    flag mapping end*/
    //end