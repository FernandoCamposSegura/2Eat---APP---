package com.fcampos.toeatapp.adapter;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.contract.FavouriteDeleteContract;
import com.fcampos.toeatapp.contract.FavouriteItemContract;
import com.fcampos.toeatapp.contract.FavouriteRegisterContract;
import com.fcampos.toeatapp.db.AppDataBase;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.domain.Favourite;
import com.fcampos.toeatapp.domain.MyFavourite;
import com.fcampos.toeatapp.presenter.FavouriteDeletePresenter;
import com.fcampos.toeatapp.presenter.FavouriteItemPresenter;
import com.fcampos.toeatapp.presenter.FavouriteRegisterPresenter;
import com.fcampos.toeatapp.view.EstablishmentDetailView;
import com.fcampos.toeatapp.view.EstablishmentUpdateView;
import com.fcampos.toeatapp.view.UserLoginView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Locale;

public class EstablishmentAdapter extends RecyclerView.Adapter<EstablishmentAdapter.EstablishmentHolder> implements FavouriteRegisterContract.View {

    private Context context;
    private List<Establishment> establishmentList;
    private FavouriteRegisterPresenter favouriteRegisterPresenter;
    private FavouriteDeletePresenter favouriteDeletePresenter;

    public EstablishmentAdapter(Context context, List<Establishment> dataList) {
        this.context = context;
        this.establishmentList = dataList;
        favouriteRegisterPresenter = new FavouriteRegisterPresenter(this);
    }

    @Override
    public EstablishmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_establishment_list_item, parent, false);
        return new EstablishmentHolder(view);
    }

    @Override
    public void onBindViewHolder(EstablishmentHolder holder, int position) {
        holder.tv_Name_EstablishmentList.setText(establishmentList.get(position).getName().toUpperCase(Locale.ROOT));
        holder.tv_Description_EstablishmentList.setText(establishmentList.get(position).getDescription());
        holder.tv_Address_EstablishmentList.setText(establishmentList.get(position).getAdress());
        updateButtonState(holder, establishmentList.get(position));
    }

    @Override
    public int getItemCount() {
        return establishmentList.size();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void updateButtonState(EstablishmentHolder holder, Establishment establishment) {
        if(holder.isAlreadyInFavouriteList(establishment.getId()))
            holder.starButton.setBackgroundResource(R.drawable.full_star);
    }

    public class EstablishmentHolder extends RecyclerView.ViewHolder implements FavouriteItemContract.View, FavouriteDeleteContract.View {

        public TextView tv_Name_EstablishmentList;
        public TextView tv_Description_EstablishmentList;
        public TextView tv_Address_EstablishmentList;
        public Button starButton;
        public Button updateButton = new Button(context);
        public LinearLayout lnly_Content;
        public FavouriteItemPresenter favouriteItemPresenter;
        public FavouriteDeletePresenter favouriteDeletePresenter;
        public View parentView;

        @SuppressLint("ClickableViewAccessibility")
        public EstablishmentHolder(View itemView) {
            super(itemView);
            parentView = itemView;

            favouriteItemPresenter = new FavouriteItemPresenter(this);
            favouriteDeletePresenter = new FavouriteDeletePresenter(this);

            tv_Name_EstablishmentList = itemView.findViewById(R.id.tv_Name_EstablishmentList);
            tv_Description_EstablishmentList = itemView.findViewById(R.id.tv_Description_EstablishmentList);
            tv_Address_EstablishmentList = itemView.findViewById(R.id.tv_Adress_EstablishmentList);
            starButton = itemView.findViewById(R.id.star_button);
            lnly_Content = itemView.findViewById(R.id.lnly_Content);

            applyAdminPermissions();
            updateButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    float scaleFactor = 0.9f; // Escala de reducción de tamaño
                    ValueAnimator animator;
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            animator = ValueAnimator.ofFloat(1f, scaleFactor);
                            animator.setDuration(200); // Duración de la animación en milisegundos
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float scale = (float) valueAnimator.getAnimatedValue();
                                    updateButton.setScaleX(scale);
                                    updateButton.setScaleY(scale);
                                }
                            });
                            animator.start();
                            return true;
                        case MotionEvent.ACTION_UP:
                            animator = ValueAnimator.ofFloat(scaleFactor, 1f);
                            animator.setDuration(200); // Duración de la animación en milisegundos
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float scale = (float) valueAnimator.getAnimatedValue();
                                    updateButton.setScaleX(scale);
                                    updateButton.setScaleY(scale);
                                }
                            });
                            animator.start();
                            updateEstablishment(getAdapterPosition());
                        case MotionEvent.ACTION_CANCEL:
                            animator = ValueAnimator.ofFloat(scaleFactor, 1f);
                            animator.setDuration(200); // Duración de la animación en milisegundos
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float scale = (float) valueAnimator.getAnimatedValue();
                                    updateButton.setScaleX(scale);
                                    updateButton.setScaleY(scale);
                                }
                            });
                            animator.start();
                            return true;
                    }
                    return false;
                }
            });

            starButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    float scaleFactor = 0.9f; // Escala de reducción de tamaño
                    ValueAnimator animator;
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            animator = ValueAnimator.ofFloat(1f, scaleFactor);
                            animator.setDuration(200); // Duración de la animación en milisegundos
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float scale = (float) valueAnimator.getAnimatedValue();
                                    starButton.setScaleX(scale);
                                    starButton.setScaleY(scale);
                                }
                            });
                            animator.start();
                            return true;
                        case MotionEvent.ACTION_UP:
                            animator = ValueAnimator.ofFloat(scaleFactor, 1f);
                            animator.setDuration(200); // Duración de la animación en milisegundos
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float scale = (float) valueAnimator.getAnimatedValue();
                                    starButton.setScaleX(scale);
                                    starButton.setScaleY(scale);
                                }
                            });
                            animator.start();
                            addFavourite(getAdapterPosition());
                        case MotionEvent.ACTION_CANCEL:
                            animator = ValueAnimator.ofFloat(scaleFactor, 1f);
                            animator.setDuration(200); // Duración de la animación en milisegundos
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float scale = (float) valueAnimator.getAnimatedValue();
                                    starButton.setScaleX(scale);
                                    starButton.setScaleY(scale);
                                }
                            });
                            animator.start();
                            return true;
                    }
                    return false;
                }
            });
            lnly_Content.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    float scaleFactor = 0.9f; // Escala de reducción de tamaño
                    ValueAnimator animator;
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            animator = ValueAnimator.ofFloat(1f, scaleFactor);
                            animator.setDuration(300); // Duración de la animación en milisegundos
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float scale = (float) valueAnimator.getAnimatedValue();
                                    lnly_Content.setScaleX(scale);
                                    lnly_Content.setScaleY(scale);
                                }
                            });
                            animator.start();
                            return true;
                        case MotionEvent.ACTION_UP:
                            animator = ValueAnimator.ofFloat(scaleFactor, 1f);
                            animator.setDuration(300); // Duración de la animación en milisegundos
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float scale = (float) valueAnimator.getAnimatedValue();
                                    lnly_Content.setScaleX(scale);
                                    lnly_Content.setScaleY(scale);
                                }
                            });
                            animator.start();
                            viewEstablishment(getAdapterPosition());
                        case MotionEvent.ACTION_CANCEL:
                            animator = ValueAnimator.ofFloat(scaleFactor, 1f);
                            animator.setDuration(300); // Duración de la animación en milisegundos
                            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float scale = (float) valueAnimator.getAnimatedValue();
                                    lnly_Content.setScaleX(scale);
                                    lnly_Content.setScaleY(scale);
                                }
                            });
                            animator.start();
                            return true;
                    }
                    return false;
                }
            });
        }

        private void viewEstablishment(int adapterPosition) {

            Establishment establishment = establishmentList.get(adapterPosition);

            Intent intent = new Intent(context, EstablishmentDetailView.class);
            intent.putExtra("establishmentId", establishment.getId());
            context.startActivity(intent);
        }

        private void addFavourite(int adapterPosition) {
            Establishment establishment = establishmentList.get(adapterPosition);
            final AppDataBase db = Room.databaseBuilder(context, AppDataBase.class, "myfavourite")
                    .allowMainThreadQueries().build();

            if(!isAlreadyInFavouriteList(establishment.getId())) {

                //DDBB Global
                Favourite favourite = new Favourite(UserLoginView.USER_ID, establishment.getId());
                favouriteRegisterPresenter.registerFavourite(favourite);

                MyFavourite myFavourite = new MyFavourite(establishment.getId());
                db.myFavouriteDAO().insert(myFavourite);
                starButton.setBackgroundResource(R.drawable.full_star);
            }
            else {

                //DDBB Global
                MyFavourite myFavourite = db.myFavouriteDAO().getMyFavouriteById(establishment.getId());
                db.myFavouriteDAO().delete(myFavourite);
                favouriteItemPresenter.getFavourite(UserLoginView.USER_ID, establishment.getId());

                starButton.setBackgroundResource(R.drawable.empty_star);
            }
        }

        private boolean isAlreadyInFavouriteList(long establishmentId) {
            final AppDataBase db = Room.databaseBuilder(context, AppDataBase.class, "myfavourite")
                    .allowMainThreadQueries().build();
            List<MyFavourite> myFavouriteList = db.myFavouriteDAO().getAll();

            for (MyFavourite myFavourite: myFavouriteList) {
                if(myFavourite.getEstablishment_id() == establishmentId)
                    return true;
            }

            return false;
        }

        public void deleteFavourite(List<Favourite> favouriteList) {
            for (Favourite favourite: favouriteList
                 ) {
                favouriteDeletePresenter.deleteFavourite(favourite.getId());
            }
        }

        public void applyAdminPermissions() {
            if (UserLoginView.ROLE == "ADMIN") {
                Typeface font = ResourcesCompat.getFont(context, R.font.amaranth);
                updateButton.setTypeface(font);
                updateButton.setText("UPDATE");
                updateButton.setTextColor(Color.parseColor("#FFFFFF"));
                updateButton.setBackgroundColor(Color.parseColor("#2A8C4A"));;
                lnly_Content.addView(updateButton);
            }
        }

        public void updateEstablishment(int adapterPosition) {
            Establishment establishment = establishmentList.get(adapterPosition);

            Intent intent = new Intent(context, EstablishmentUpdateView.class);
            intent.putExtra("establishmentId", establishment.getId());
            context.startActivity(intent);
        }

        @Override
        public void showMessage(String message) {
            Snackbar.make(tv_Description_EstablishmentList, message, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }


}
