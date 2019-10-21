package com.example.assigment_android_nangcao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_android_nangcao.R;
import com.example.assigment_android_nangcao.model.GioHang;
import com.example.assigment_android_nangcao.model.KhoaHoc;

import java.util.List;

public class AdapterGioHangRV extends RecyclerView.Adapter<AdapterGioHangRV.ItemHolder> {
    private Context context;

    private List<GioHang> gioHangList;

    public AdapterGioHangRV(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_gio_hang, viewGroup, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        GioHang gioHang = gioHangList.get(i);
        itemHolder.tvGHTenKhoaHoc.setText(gioHang.tenKhoaHoc);
        itemHolder.tvGHGiaKhoaHoc.setText(gioHang.giaKhoaHoc);
        itemHolder.tvGHTenGiangVien.setText(gioHang.giangVienKhoaHoc);

    }

    @Override
    public int getItemCount() {
        if (gioHangList == null) return 0;
        return gioHangList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        public TextView tvGHTenKhoaHoc, tvGHGiaKhoaHoc, tvGHTenGiangVien;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvGHTenKhoaHoc = itemView.findViewById(R.id.tvGHTenKhoaHoc);
            tvGHGiaKhoaHoc = itemView.findViewById(R.id.tvGHGiaKhoaHoc);
            tvGHTenGiangVien = itemView.findViewById(R.id.tvGHTenGiangVien);
        }
    }

    // ------------------------------------------ BẮT SỰ KIỆN ONCLICK ---------------------------------------------------

    public static class ItemClickSupport {
        private final RecyclerView mRecyclerView;
        private OnItemClickListener mOnItemClickListener;
        private OnItemLongClickListener mOnItemLongClickListener;
        private View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                    mOnItemClickListener.onItemClicked(mRecyclerView, holder.getAdapterPosition(), v);
                }
            }
        };
        private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                    return mOnItemLongClickListener.onItemLongClicked(mRecyclerView, holder.getAdapterPosition(), v);
                }
                return false;
            }
        };
        private RecyclerView.OnChildAttachStateChangeListener mAttachListener
                = new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                if (mOnItemClickListener != null) {
                    view.setOnClickListener(mOnClickListener);
                }
                if (mOnItemLongClickListener != null) {
                    view.setOnLongClickListener(mOnLongClickListener);
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
            }
        };

        private ItemClickSupport(RecyclerView recyclerView) {
            mRecyclerView = recyclerView;
            mRecyclerView.setTag(R.id.item_click_support, this);
            mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
        }

        public static ItemClickSupport addTo(RecyclerView view) {
            ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
            if (support == null) {
                support = new ItemClickSupport(view);
            }
            return support;
        }

        public static ItemClickSupport removeFrom(RecyclerView view) {
            ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
            if (support != null) {
                support.detach(view);
            }
            return support;
        }

        public ItemClickSupport setOnItemClickListener(OnItemClickListener listener) {
            mOnItemClickListener = listener;
            return this;
        }

        public ItemClickSupport setOnItemLongClickListener(OnItemLongClickListener listener) {
            mOnItemLongClickListener = listener;
            return this;
        }

        private void detach(RecyclerView view) {
            view.removeOnChildAttachStateChangeListener(mAttachListener);
            view.setTag(R.id.item_click_support, null);
        }

        public interface OnItemClickListener {
            void onItemClicked(RecyclerView recyclerView, int position, View v);
        }

        public interface OnItemLongClickListener {
            boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
        }
    }
}
