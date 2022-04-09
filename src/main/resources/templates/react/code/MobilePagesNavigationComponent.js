import React from 'react';
import {PRIMARY_COLOR} from "../info/ColorUtils";
import {NavLink, useHistory} from "react-router-dom";

export default function MobilePagesNavigationComponent() {
    const history = useHistory();
    return (
        <div style={{ background: PRIMARY_COLOR }} className={"h-screen p-5"}>
            <div className={"top-0 right-0 static text-white cursor-pointer mr-0"} onClick={() => history.goBack()}>
                <svg xmlns="http://www.w3.org/2000/svg" className="h-6 mr-0" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                </svg>
            </div>
            <div className={"flex flex-col justify-center items-center"} style={{ height: "70vh" }}>
                <div className={"flex flex-col justify-center items-center"}>
                    <NavLink style={{ color: "white"}} className={"p-2"} to="/">Home</NavLink>
                    <NavLink style={{ color: "white"}} className={"p-2"} to="/link1">Link 1</NavLink>
                    <NavLink style={{ color: "white"}} className={"p-2"} to="/link2">Link 2</NavLink>
                    <NavLink style={{ color: "white"}} className={"p-2"} to="/link3">Link 3</NavLink>
                </div>
            </div>
            <div className={"absolute inset-x-0 bottom-0 h-16"}>
                <div className={"flex flex-col justify-center items-center text-white bottom-0 right-0 relative"}>
                    <h3>Legora Generated Website</h3>
                    <p>Version 1.0.0</p>
                </div>
            </div>
        </div>
    );
}