package com.tixon.timemanagement.databinding;
import com.tixon.timemanagement.R;
import com.tixon.timemanagement.BR;
import android.view.View;
public class FragmentEisenhowerBinding extends android.databinding.ViewDataBinding {
    
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.urgentAndImportant, 1);
        sViewsWithIds.put(R.id.notUrgentAndImportant, 2);
        sViewsWithIds.put(R.id.urgentAndNotImportant, 3);
        sViewsWithIds.put(R.id.notUrgentAndNotImportant, 4);
        sViewsWithIds.put(R.id.fab, 5);
    }
    // views
    public final android.support.design.widget.FloatingActionButton fab;
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    public final android.widget.FrameLayout notUrgentAndImportant;
    public final android.widget.FrameLayout notUrgentAndNotImportant;
    public final android.widget.FrameLayout urgentAndImportant;
    public final android.widget.FrameLayout urgentAndNotImportant;
    // variables
    // values
    // listeners
    
    public FragmentEisenhowerBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.fab = (android.support.design.widget.FloatingActionButton) bindings[5];
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.notUrgentAndImportant = (android.widget.FrameLayout) bindings[2];
        this.notUrgentAndNotImportant = (android.widget.FrameLayout) bindings[4];
        this.urgentAndImportant = (android.widget.FrameLayout) bindings[1];
        this.urgentAndNotImportant = (android.widget.FrameLayout) bindings[3];
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
    
    public static FragmentEisenhowerBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentEisenhowerBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentEisenhowerBinding>inflate(inflater, com.tixon.timemanagement.R.layout.fragment_eisenhower, root, attachToRoot, bindingComponent);
    }
    public static FragmentEisenhowerBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentEisenhowerBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.tixon.timemanagement.R.layout.fragment_eisenhower, null, false), bindingComponent);
    }
    public static FragmentEisenhowerBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static FragmentEisenhowerBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_eisenhower_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentEisenhowerBinding(bindingComponent, view);
    }
}
    /* flag mapping
        flag 0: INVALIDATE ANY
    flag mapping end*/
    //end