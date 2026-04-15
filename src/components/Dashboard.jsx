import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function Dashboard() {
  const navigate = useNavigate();

  const [data, setData] = useState([]);

  const [student, setStudent] = useState({
    name: "", email: "", age: "", mobile: "",
    college: "", address: "", pincode: "",
    city: "", state: ""
  });

  const user = JSON.parse(localStorage.getItem("user"));

  // ✅ FETCH DATA (moved above useEffect)
  const fetchData = () => {
    axios.get(`http://localhost:9090/api/students/${user.id}`)
      .then(res => setData(res.data))
      .catch(err => console.log(err));
  };

  // 🔐 Redirect if not logged in
  useEffect(() => {
    if (!user) {
      navigate("/");
    } else {
      fetchData();
    }
  }, []);

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!student.name || !student.email) {
      alert("Name and Email are required");
      return;
    }

    axios.post(`http://localhost:9090/api/students/${user.id}`, student)
      .then(() => {
        fetchData();

        // 🔄 reset form
        setStudent({
          name: "", email: "", age: "", mobile: "",
          college: "", address: "", pincode: "",
          city: "", state: ""
        });
      })
      .catch(err => console.log(err));
  };

  const handleDelete = (id) => {
    axios.delete(`http://localhost:9090/api/students/${id}`)
      .then(() => fetchData())
      .catch(err => console.log(err));
  };

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/");
  };

  return (
    <div>
      {/* 🔥 NAVBAR */}
      <nav className="navbar navbar-dark bg-dark px-3">
        <span className="navbar-brand">Student Dashboard</span>
        <button className="btn btn-danger" onClick={handleLogout}>
          Logout
        </button>
      </nav>

      <div className="container mt-4">

        {/* 🧾 FORM */}
        <div className="card p-4 shadow mb-4">
          <h4 className="mb-3 text-center">Add Student</h4>

          <form onSubmit={handleSubmit}>
            <div className="row">

              <div className="col-md-6 mb-2">
                <input
                  name="name"
                  value={student.name}
                  className="form-control"
                  placeholder="Name"
                  onChange={handleChange}
                />
              </div>

              <div className="col-md-6 mb-2">
                <input
                  name="email"
                  value={student.email}
                  className="form-control"
                  placeholder="Email"
                  onChange={handleChange}
                />
              </div>

              <div className="col-md-4 mb-2">
                <input
                  name="age"
                  value={student.age}
                  className="form-control"
                  placeholder="Age"
                  onChange={handleChange}
                />
              </div>

              <div className="col-md-4 mb-2">
                <input
                  name="mobile"
                  value={student.mobile}
                  className="form-control"
                  placeholder="Mobile"
                  onChange={handleChange}
                />
              </div>

              <div className="col-md-4 mb-2">
                <input
                  name="college"
                  value={student.college}
                  className="form-control"
                  placeholder="College"
                  onChange={handleChange}
                />
              </div>

              <div className="col-md-12 mb-2">
                <input
                  name="address"
                  value={student.address}
                  className="form-control"
                  placeholder="Address"
                  onChange={handleChange}
                />
              </div>

              <div className="col-md-4 mb-2">
                <input
                  name="pincode"
                  value={student.pincode}
                  className="form-control"
                  placeholder="Pincode"
                  onChange={handleChange}
                />
              </div>

              <div className="col-md-4 mb-2">
                <input
                  name="city"
                  value={student.city}
                  className="form-control"
                  placeholder="City"
                  onChange={handleChange}
                />
              </div>

              <div className="col-md-4 mb-2">
                <input
                  name="state"
                  value={student.state}
                  className="form-control"
                  placeholder="State"
                  onChange={handleChange}
                />
              </div>

            </div>

            <button className="btn btn-primary w-100 mt-3">
              Add Student
            </button>
          </form>
        </div>

        {/* 📊 TABLE */}
        <h4 className="mb-3">Your Students</h4>

        <table className="table table-bordered table-striped shadow">
          <thead className="table-dark">
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Mobile</th>
              <th>College</th>
              <th>City</th>
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {data.length > 0 ? (
              data.map((s) => (
                <tr key={s.id}>
                  <td>{s.name}</td>
                  <td>{s.email}</td>
                  <td>{s.mobile}</td>
                  <td>{s.college}</td>
                  <td>{s.city}</td>

                  <td>
                    <button
                      className="btn btn-danger btn-sm"
                      onClick={() => handleDelete(s.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="6" className="text-center">
                  No Data Found
                </td>
              </tr>
            )}
          </tbody>
        </table>

      </div>
    </div>
  );
}

export default Dashboard;
