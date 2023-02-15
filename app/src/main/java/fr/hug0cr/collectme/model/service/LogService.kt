package fr.hug0cr.collectme.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}