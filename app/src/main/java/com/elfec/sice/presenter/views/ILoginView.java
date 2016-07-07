package com.elfec.sice.presenter.views;

/**
 * Created by drodriguez on 07/07/2016.
 * Login view abstraction
 */
public interface ILoginView extends IProcessView<String> {
    /**
     * Obtiene el nombre de usuario
     *
     * @return el nombre de usuario
     */
    String getUsername();

    /**
     * Obtiene el token de registro
     *
     * @return el token de registro
     */
    String getRegToken();
}
